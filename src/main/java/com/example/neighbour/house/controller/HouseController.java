package com.example.neighbour.house.controller;

import com.example.neighbour.house.dto.HouseCreationRequestDto;
import com.example.neighbour.house.dto.HouseDto;
import com.example.neighbour.house.dto.HouseUpdateRequestDto;
import com.example.neighbour.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return ResponseEntity.of(houseService.getById(id).map(HouseDto::new));
    }

    @GetMapping
    public ResponseEntity<Page<HouseDto>> getAll( @PageableDefault(size=20) Pageable pageable ) {
        return ResponseEntity.ok(houseService.getAll(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        houseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HouseDto> update(@Valid @RequestBody HouseUpdateRequestDto request) {
        return ResponseEntity.of(houseService.update(request));
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
