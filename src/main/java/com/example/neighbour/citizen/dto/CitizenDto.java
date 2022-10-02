package com.example.neighbour.citizen.dto;

import com.example.neighbour.citizen.model.Citizen;
import com.example.neighbour.flat.dto.FlatDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CitizenDto {
    UUID id;
    String name;

    FlatDto flat;

    public CitizenDto(Citizen citizen) {
        this.id = citizen.getUid();
        this.name = citizen.getName();

        if (citizen.getFlat() != null) {
            this.flat = new FlatDto(citizen.getFlat());
        }
    }

    public Citizen toModel() {
        Citizen citizen = new Citizen();
        citizen.setName(this.getName());
        citizen.setUid(this.getId());
        return citizen;
    }
}
