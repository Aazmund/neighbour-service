package com.example.neighbour.flat.dto;

import com.example.neighbour.flat.model.Flat;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class FlatUpdateRequestDto {
    UUID id;
    private String number;

    public Flat toModel() {
        Flat flat = new Flat();
        flat.setNumber(this.number);
        return flat;
    }
}
