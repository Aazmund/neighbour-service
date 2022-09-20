package com.example.neighbour.controller.citizen;

import com.example.neighbour.controller.citizen.dto.CitizenCreationRequestDto;
import com.example.neighbour.controller.citizen.dto.CitizenDto;
import com.example.neighbour.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/citizen")
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    @PostMapping
    public ResponseEntity<CitizenDto> create(@RequestBody CitizenCreationRequestDto request) {
        return ResponseEntity.ok(new CitizenDto(citizenService.create(request.create())));
    }

}
