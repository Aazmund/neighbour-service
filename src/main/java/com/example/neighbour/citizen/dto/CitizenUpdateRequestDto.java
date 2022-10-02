package com.example.neighbour.citizen.dto;

import com.example.neighbour.citizen.model.Citizen;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CitizenUpdateRequestDto extends CitizenCreationRequestDto {
    private UUID id;
    private String name;

    public Citizen toModel() {
        Citizen citizen = new Citizen();
        citizen.setName(name);

        return citizen;
    }
}
