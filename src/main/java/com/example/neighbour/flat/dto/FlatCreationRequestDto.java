package com.example.neighbour.flat.dto;

import com.example.neighbour.flat.model.Flat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlatCreationRequestDto {
    @NotNull
    private String number;

    public Flat toModel() {
        Flat flat = new Flat();
        flat.setNumber(this.number);
        return flat;
    }
}