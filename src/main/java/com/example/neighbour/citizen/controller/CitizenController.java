package com.example.neighbour.citizen.controller;

import com.example.neighbour.citizen.dto.CitizenCreationRequestDto;
import com.example.neighbour.citizen.dto.CitizenDto;
import com.example.neighbour.citizen.dto.CitizenUpdateRequestDto;
import com.example.neighbour.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/citizen")
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    @PostMapping
    public ResponseEntity<CitizenDto> create(@RequestBody CitizenCreationRequestDto request) {
        return ResponseEntity.ok(citizenService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(citizenService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public ResponseEntity<Page<CitizenDto>> getAll() {
        return ResponseEntity.ok(citizenService.getAll(Pageable.unpaged()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        citizenService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CitizenDto> create(@RequestBody CitizenUpdateRequestDto request) {
        return ResponseEntity.ok(citizenService.update(request).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
