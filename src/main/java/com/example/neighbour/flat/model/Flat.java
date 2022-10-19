package com.example.neighbour.flat.model;

import com.example.neighbour.citizen.model.Citizen;
import com.example.neighbour.house.model.House;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "flats")
@SQLDelete(sql = "UPDATE flats SET deleted_at = now() WHERE uid = ?")
@Where(clause = "deleted_at is null")
@Data
public class Flat {
    @Id
    @GeneratedValue
    UUID uid;

    private String number;

    @ManyToOne
    @JoinColumn(name = "house_uid")
    private House house;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flat")
    private List<Citizen> citizens;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updated_at;
}
