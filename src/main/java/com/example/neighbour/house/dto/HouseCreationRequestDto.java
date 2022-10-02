package com.example.neighbour.house.dto;

import com.example.neighbour.house.model.House;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class HouseCreationRequestDto {
    @NotNull
    private String number;

    public House toModel() {
        House house = new House();
        house.setNumber(this.number);
        return house;
    }
}
