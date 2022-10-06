package com.example.neighbour.street.dto;

import com.example.neighbour.street.model.Street;
import lombok.Data;
import java.util.UUID;

@Data
public class StreetUpdateRequestDto extends StreetCreationRequestDto {
    private UUID uid;
    private String name;

    public Street toModel() {
        Street street = new Street();
        street.setName(name);
        return street;
    }
}
