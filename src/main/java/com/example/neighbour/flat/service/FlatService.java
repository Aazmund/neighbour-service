package com.example.neighbour.flat.service;

import com.example.neighbour.flat.dto.FlatCreationRequestDto;
import com.example.neighbour.flat.dto.FlatDto;
import com.example.neighbour.flat.dto.FlatUpdateRequestDto;
import com.example.neighbour.flat.model.Flat;
import com.example.neighbour.flat.repository.FlatRepository;
import com.example.neighbour.house.model.House;
import com.example.neighbour.house.service.HouseService;
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
public class FlatService {

    private final FlatRepository flatRepository;
    private final HouseService houseService;

    @Transactional
    public FlatDto create(FlatCreationRequestDto flat){
        if (flatRepository.existsByNumber(flat.getNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Flat model = flatRepository.save(flat.toModel());
        return new FlatDto(model);
    }

    public Optional<Flat> getById(UUID uid) {
        return flatRepository.findById(uid);
    }

    public Page<FlatDto> getAll(Pageable pageable) {
        return flatRepository.findAll(pageable).map(FlatDto::new);
    }

    @Transactional
    public Optional<FlatDto> update(FlatUpdateRequestDto flat) {
        Optional<Flat> entity = flatRepository.findById(flat.getId()).map(
            it -> {
                Flat model = flat.toModel();
                Optional.ofNullable(model.getNumber()).ifPresent(it::setNumber);
                return flatRepository.save(it);
            }
        );
        return entity.map(FlatDto::new);
    }

    public void delete(UUID uid) {
        flatRepository.deleteById(uid);
    }

    @Transactional
    public FlatDto attachHouse(UUID id, UUID houseId) {
        Flat flat = flatRepository.getReferenceById(id);
        Optional<House> house = houseService.getById(houseId);

        if (house.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        flat.setHouse(house.get());

        return new FlatDto(flatRepository.saveAndFlush(flat));
    }

    @Transactional
    public FlatDto detachHouse(UUID id) {
        Flat flat = flatRepository.getReferenceById(id);

        if (flat.getHouse() == null) {
            return new FlatDto(flat);
        }

        flat.setHouse(null);

        return new FlatDto(flatRepository.saveAndFlush(flat));
    }
}
