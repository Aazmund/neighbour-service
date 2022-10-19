package com.example.neighbour.citizen.controller;

import com.example.neighbour.citizen.dto.CitizenCreationRequestDto;
import com.example.neighbour.citizen.dto.CitizenDto;
import com.example.neighbour.citizen.dto.CitizenInfoDto;
import com.example.neighbour.citizen.dto.CitizenUpdateRequestDto;
import com.example.neighbour.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/citizen")
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    @PostMapping
    public ResponseEntity<CitizenDto> create(@Valid @RequestBody CitizenCreationRequestDto request) {
        return ResponseEntity.ok(citizenService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.of(citizenService.getById(id).map(CitizenDto::new));
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<CitizenInfoDto> getInfoById(@PathVariable UUID id) {
        return ResponseEntity.of(citizenService.getById(id).map(CitizenDto::new).map(CitizenInfoDto::new));
    }

    @GetMapping
    public ResponseEntity<Page<CitizenDto>> getAll(@PageableDefault(size=20) Pageable pageable) {
        return ResponseEntity.ok(citizenService.getAll(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        citizenService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CitizenDto> create(@RequestBody CitizenUpdateRequestDto request) {
        return ResponseEntity.of(citizenService.update(request));
    }

    @PostMapping("{id}/flat/{flatId}")
    public ResponseEntity<CitizenDto> attachFlat(@PathVariable UUID id, @PathVariable UUID flatId) {
        return ResponseEntity.ok(citizenService.attachFlat(id, flatId));
    }

    @PostMapping("{id}/detach")
    public ResponseEntity<CitizenDto> detachFlat(@PathVariable UUID id) {
        return ResponseEntity.ok(citizenService.detachFlat(id));
    }
}
