package com.example.neighbour.citizen.dto;

import com.example.neighbour.citizen.model.Citizen;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CitizenDto {
    UUID id;
    String name;

    public CitizenDto(Citizen citizen) {
        this.id = citizen.getUid();
        this.name = citizen.getName();
    }

    public Citizen toModel() {
        Citizen citizen = new Citizen();
        citizen.setName(this.getName());
        citizen.setUid(this.getId());
        return citizen;
    }
}
