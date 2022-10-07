package com.example.neighbour.flat.dto;

import com.example.neighbour.flat.model.Flat;
import com.example.neighbour.house.dto.HouseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class FlatDto {
    UUID id;
    String number;
    HouseDto house;

    Integer totalCitizens;

    public FlatDto(Flat flat){
        this.id = flat.getUid();
        this.number = flat.getNumber();

        if (flat.getHouse() != null) {
            this.house = new HouseDto(flat.getHouse());
        }

        if (flat.getCitizens() != null) {
            this.totalCitizens = flat.getCitizens().size();
        }
    }

    public Flat toModel() {
        Flat flat = new Flat();
        flat.setUid(this.getId());
        flat.setNumber(this.getNumber());
        return flat;
    }
}
