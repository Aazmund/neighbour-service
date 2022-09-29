package com.example.neighbour.street.controller;

import com.example.neighbour.street.dto.StreetCreationRequestDto;
import com.example.neighbour.street.dto.StreetDto;
import com.example.neighbour.street.dto.StreetUpdateRequestDto;
import com.example.neighbour.street.service.StreetService;
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
        return ResponseEntity.ok(streetService.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public ResponseEntity<Page<StreetDto>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(streetService.getAll(pageRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        streetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<StreetDto> update(@Valid @RequestBody StreetUpdateRequestDto request) {
        return ResponseEntity.ok(streetService.update(request).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
