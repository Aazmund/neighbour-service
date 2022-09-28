package com.example.neighbour.street.dto;

import com.example.neighbour.street.model.Street;
import lombok.Data;

@Data
public class StreetCreationRequestDto {
    private String name;

    public Street toModel() {
        Street street = new Street();
        street.setName(this.name);
        return street;
    }
}

