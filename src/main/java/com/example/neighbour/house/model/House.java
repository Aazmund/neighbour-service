package com.example.neighbour.house.model;

import com.example.neighbour.street.model.Street;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "houses")
@Data
public class House {
    @Id
    @GeneratedValue
    UUID uid;

    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_uid")
    private Street street;
}
