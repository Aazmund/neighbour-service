package com.example.neighbour.house.controller;

import com.example.neighbour.house.dto.HouseCreationRequestDto;
import com.example.neighbour.house.dto.HouseDto;
import com.example.neighbour.house.dto.HouseUpdateRequestDto;
import com.example.neighbour.house.service.HouseService;
import com.example.neighbour.street.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("api/v1/house")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;

    @PostMapping
    public ResponseEntity<HouseDto> create(@Valid @RequestBody HouseCreationRequestDto request) {
        return ResponseEntity.ok(houseService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(houseService.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public ResponseEntity<Page<HouseDto>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(houseService.getAll(pageRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        houseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HouseDto> update(@Valid @RequestBody HouseUpdateRequestDto request) {
        return ResponseEntity.ok(houseService.update(request).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("{id}/street/{streetId}")
    public ResponseEntity<HouseDto> attachStreet(@PathVariable UUID id, @PathVariable UUID streetId) {
        return ResponseEntity.ok(houseService.attachStreet(id, streetId));
    }

    @PostMapping("{id}/detach")
    public ResponseEntity<HouseDto> detachStreet(@PathVariable UUID id) {
        return ResponseEntity.ok(houseService.detachStreet(id));
    }
}
