package com.example.neighbour.controller.citizen.dto;

import com.example.neighbour.model.Citizen;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CitizenDto {
    String name;

    public CitizenDto(Citizen citizen) {
        this.name = citizen.getName();
    }
}
