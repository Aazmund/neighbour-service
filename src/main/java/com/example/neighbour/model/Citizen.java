package com.example.neighbour.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "citizens")
@Data
public class Citizen {
    @Id
    UUID uid;

    private String name;

    @ManyToOne
    @JoinColumn(name="flat_uid", nullable = false)
    private Flat flat;
}