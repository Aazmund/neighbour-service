package com.example.neighbour.house.service;

import com.example.neighbour.house.dto.HouseCreationRequestDto;
import com.example.neighbour.house.dto.HouseDto;
import com.example.neighbour.house.dto.HouseUpdateRequestDto;
import com.example.neighbour.house.model.House;
import com.example.neighbour.house.repository.HouseRepository;
import com.example.neighbour.street.model.Street;
import com.example.neighbour.street.service.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final StreetService streetService;

    @Transactional
    public HouseDto create(HouseCreationRequestDto house) {
        if (houseRepository.existsByNumber(house.getNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        House model = houseRepository.save(house.toModel());
        return new HouseDto(model);
    }

    public Optional<House> getById(UUID uid) {
        return houseRepository.findById(uid);
    }

    public Page<HouseDto> getAll(Pageable pageable) {
        return houseRepository.findAll(pageable).map(HouseDto::new);
    }

    @Transactional
    public Optional<HouseDto> update(HouseUpdateRequestDto house) {
        Optional<House> entity = houseRepository.findById(house.getId()).map(
            it -> {
                House model = house.toModel();
                Optional.ofNullable(model.getNumber()).ifPresent(it::setNumber);
                return houseRepository.save(it);
            }
        );
        return entity.map(HouseDto::new);
    }

    public void delete(UUID uid) {
        houseRepository.deleteById(uid);
    }

    @Transactional
    public HouseDto attachStreet(UUID id, UUID streetId) {
        House house = houseRepository.getReferenceById(id);
        Optional<Street> street = streetService.getById(streetId);

        if (street.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        house.setStreet(street.get());

        return new HouseDto(houseRepository.saveAndFlush(house));
    }

    @Transactional
    public HouseDto detachStreet(UUID id) {
        House house = houseRepository.getReferenceById(id);

        if (house.getStreet() == null) {
            return new HouseDto(house);
        }

        house.setStreet(null);

        return new HouseDto(houseRepository.saveAndFlush(house));
    }
}
