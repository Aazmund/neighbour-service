package com.example.neighbour.house.dto;

import com.example.neighbour.house.model.House;
import com.example.neighbour.street.dto.StreetDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class HouseDto {
    UUID id;
    String number;

    StreetDto street;

    public HouseDto(House house) {
        this.id = house.getUid();
        this.number = house.getNumber();

        if (house.getStreet() != null) {
            this.street = new StreetDto(house.getStreet());
        }
    }

    public House toModel() {
        House house = new House();
        house.setUid(this.getId());
        house.setNumber(this.getNumber());
        return house;
    }
}
