package com.example.neighbour.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "flats")
@Data
public class Flat {
    @Id
    UUID uid;

    private String number;

    @OneToMany(mappedBy = "flat")
    private Set<Citizen> citizens;

}
