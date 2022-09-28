package com.example.neighbour.house.dto;

import com.example.neighbour.house.model.House;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class HouseUpdateRequestDto extends HouseCreationRequestDto {
    UUID id;
    String number;

    public House toModel() {
        House house = new House();
        house.setNumber(number);
        return house;
    }
}
