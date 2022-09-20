package com.example.neighbour.controller.citizen.dto;

import com.example.neighbour.model.Citizen;
import lombok.Data;

import java.util.UUID;

@Data
public class CitizenCreationRequestDto {
    private String name;

    public Citizen create() {
        Citizen citizen = new Citizen();
        citizen.setName(name);

        return citizen;
    }
}
