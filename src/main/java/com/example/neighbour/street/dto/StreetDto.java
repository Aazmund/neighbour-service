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

    public StreetDto(Street street){
        this.id = street.getUid();
        this.name = street.getName();
    }

    public Street toModel(){
        Street street = new Street();
        street.setUid(this.getId());
        street.setName(this.getName());
        return street;
    }
}
