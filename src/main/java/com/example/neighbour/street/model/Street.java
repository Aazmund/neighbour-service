package com.example.neighbour.street.model;

import com.example.neighbour.house.model.House;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "streets")
@Data
public class Street {
    @Id
    @GeneratedValue
    UUID uid;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "street")
    private List<House> houses;
}
