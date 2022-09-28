package com.example.neighbour.citizen.model;

import com.example.neighbour.flat.model.Flat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "citizens")
@Data
public class Citizen {
    @Id
    @GeneratedValue
    UUID uid;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flat_uid")
    private Flat flat;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}