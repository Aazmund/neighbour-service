package com.example.neighbour.house.service;

import com.example.neighbour.house.dto.HouseCreationRequestDto;
import com.example.neighbour.house.dto.HouseDto;
import com.example.neighbour.house.dto.HouseUpdateRequestDto;
import com.example.neighbour.house.model.House;
import com.example.neighbour.house.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseDto create(HouseCreationRequestDto house) {
        if (houseRepository.existsByNumber(house.getNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        House model = houseRepository.save(house.toModel());
        return new HouseDto(model);
    }

    public Optional<HouseDto> getById(UUID uid) {
        return houseRepository.findById(uid).map(HouseDto::new);
    }

    public Page<HouseDto> getAll(Pageable pageable) {
        return houseRepository.findAll(pageable).map(HouseDto::new);
    }

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
}
