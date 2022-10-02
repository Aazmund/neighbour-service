package com.example.neighbour.citizen.controller;

import com.example.neighbour.citizen.dto.CitizenCreationRequestDto;
import com.example.neighbour.citizen.dto.CitizenDto;
import com.example.neighbour.citizen.dto.CitizenUpdateRequestDto;
import com.example.neighbour.citizen.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return ResponseEntity.ok(citizenService.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public ResponseEntity<Page<CitizenDto>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(citizenService.getAll(pageRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        citizenService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CitizenDto> create(@RequestBody CitizenUpdateRequestDto request) {
        return ResponseEntity.ok(citizenService.update(request).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
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
