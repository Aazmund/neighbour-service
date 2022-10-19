package com.example.neighbour.street.model;

import com.example.neighbour.house.model.House;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
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

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
