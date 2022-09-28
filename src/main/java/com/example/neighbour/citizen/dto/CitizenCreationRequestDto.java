package com.example.neighbour.citizen.dto;

import com.example.neighbour.citizen.model.Citizen;
import lombok.Data;

@Data
public class CitizenCreationRequestDto {
    private String name;

    public Citizen toModel() {
        Citizen citizen = new Citizen();
        citizen.setName(name);

        return citizen;
    }
}
