package com.example.neighbour.house.controller;

import com.example.neighbour.house.dto.HouseCreationRequestDto;
import com.example.neighbour.house.dto.HouseDto;
import com.example.neighbour.house.dto.HouseUpdateRequestDto;
import com.example.neighbour.house.model.House;
import com.example.neighbour.house.repository.HouseRepository;
import com.example.neighbour.house.service.HouseService;
import com.example.neighbour.street.dto.StreetUpdateRequestDto;
import com.example.neighbour.street.model.Street;
import com.example.neighbour.street.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/house")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    private final StreetRepository streetRepository;

    @PostMapping
    public ResponseEntity<HouseDto> create(@RequestBody HouseCreationRequestDto request) {
        return ResponseEntity.ok(houseService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(houseService.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public ResponseEntity<Page<HouseDto>> getAll() {
        return ResponseEntity.ok(houseService.getAll(Pageable.unpaged()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        houseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HouseDto> create(@RequestBody HouseUpdateRequestDto request) {
        return ResponseEntity.ok(houseService.update(request).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("{id}/street/{street_id}")
    public ResponseEntity<HouseDto> attachStreet(@PathVariable UUID id, @PathVariable UUID street_id) {
        House entity = getOne(id).getBody().toModel();
        Street street = streetRepository.getReferenceById(street_id);
        HouseUpdateRequestDto requestDto = new HouseUpdateRequestDto(entity.setStreet(street));
        return ResponseEntity.ok(houseService.update(requestDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }
}
