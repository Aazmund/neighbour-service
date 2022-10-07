package com.example.neighbour.citizen.dto;

import com.example.neighbour.citizen.model.Citizen;
import com.example.neighbour.flat.dto.FlatDto;
import com.example.neighbour.house.dto.HouseDto;
import com.example.neighbour.street.dto.StreetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenInfoDto {
    UUID id;

    Integer totalCitizens;
    Integer totalHouses;
    String flatNumber;

    public CitizenInfoDto(CitizenDto dto) {
        this.id = dto.id;


        if (dto.flat != null) {
            if (dto.flat.getTotalCitizens() != null) {
                this.totalCitizens = dto.flat.getTotalCitizens();
            }

            if (dto.flat.getNumber() != null) {
                this.flatNumber = dto.flat.getNumber();
            }

            if (dto.flat.getHouse().getStreet().getTotalHouses() != null) {
                this.totalHouses = dto.flat.getHouse().getStreet().getTotalHouses();
            }
        }
    }

}
