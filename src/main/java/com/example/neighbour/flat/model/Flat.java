package com.example.neighbour.flat.model;

import com.example.neighbour.citizen.model.Citizen;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "flats")
@Data
public class Flat {
    @Id
    @GeneratedValue
    UUID uid;

    private String number;

    @OneToMany(mappedBy = "flat")
    private Set<Citizen> citizens;

}
