package com.example.neighbour.street.dto;

import com.example.neighbour.street.model.Street;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StreetCreationRequestDto {
    @NotBlank
    private String name;

    public Street toModel() {
        Street street = new Street();
        street.setName(this.name);
        return street;
    }
}

