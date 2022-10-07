package com.example.neighbour.street.dto;

import com.example.neighbour.street.model.Street;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StreetDto {
    UUID id;
    String name;

    Integer totalHouses;

    public StreetDto(Street street){
        this.id = street.getUid();
        this.name = street.getName();

        if (street.getHouses() != null) {
            this.totalHouses = street.getHouses().size();
        }
    }

    public Street toModel(){
        Street street = new Street();
        street.setUid(this.getId());
        street.setName(this.getName());
        return street;
    }
}
