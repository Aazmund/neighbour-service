package com.example.neighbour.house.dto;

import com.example.neighbour.house.model.House;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class HouseDto {
    UUID id;
    String number;

    public HouseDto(House house) {
        this.id = house.getUid();
        this.number = house.getNumber();
    }

    public House toModel() {
        House house = new House();
        house.setUid(this.getId());
        house.setNumber(this.getNumber());
        return house;
    }
}
