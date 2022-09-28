package com.example.neighbour.street.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "streets")
@Data
public class Street {
    @Id
    @GeneratedValue
    UUID uid;

    private String name;
}
