package com.example.neighbour.flat.controller;

import com.example.neighbour.flat.dto.FlatCreationRequestDto;
import com.example.neighbour.flat.dto.FlatDto;
import com.example.neighbour.flat.dto.FlatUpdateRequestDto;
import com.example.neighbour.flat.service.FlatService;
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
@RequestMapping("api/v1/flat")
@RequiredArgsConstructor
public class FlatController {
    private final FlatService flatService;

    @PostMapping
    public ResponseEntity<FlatDto> create(@Valid @RequestBody FlatCreationRequestDto request) {
        return ResponseEntity.ok(flatService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlatDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(flatService.getById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public ResponseEntity<Page<FlatDto>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(flatService.getAll(pageRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        flatService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<FlatDto> update(@Valid @RequestBody FlatUpdateRequestDto request) {
        return ResponseEntity.ok(flatService.update(request).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("{id}/house/{houseId}")
    public ResponseEntity<FlatDto> attachHouse(@PathVariable UUID id, @PathVariable UUID houseId){
        return ResponseEntity.ok(flatService.attachHouse(id, houseId));
    }

    @PostMapping("{id}/detach")
    public ResponseEntity<FlatDto> detachHouse(@PathVariable UUID id){
        return ResponseEntity.ok(flatService.detachHouse(id));
    }
}
