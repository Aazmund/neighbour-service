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

    public FlatDto(Flat flat){
        this.id = flat.getUid();
        this.number = flat.getNumber();

        if (house.getStreet() != null) {
            this.house = new HouseDto(flat.getHouse());
        }
    }
}
