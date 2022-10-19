package com.example.neighbour.street.controller;

import com.example.neighbour.street.dto.StreetCreationRequestDto;
import com.example.neighbour.street.dto.StreetDto;
import com.example.neighbour.street.dto.StreetUpdateRequestDto;
import com.example.neighbour.street.service.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("api/v1/street")
@RequiredArgsConstructor
public class StreetController {
    private final StreetService streetService;

    @PostMapping
    public ResponseEntity<StreetDto> create(@Valid @RequestBody StreetCreationRequestDto request) {
        return ResponseEntity.ok(streetService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreetDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.of(streetService.getById(id).map(StreetDto::new));
    }

    @GetMapping
    public ResponseEntity<Page<StreetDto>> getAll(@PageableDefault(size=20) Pageable pageable) {
        return ResponseEntity.ok(streetService.getAll(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        streetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<StreetDto> update(@Valid @RequestBody StreetUpdateRequestDto request) {
        return ResponseEntity.of(streetService.update(request));
    }
}
