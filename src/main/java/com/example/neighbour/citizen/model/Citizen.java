package com.example.neighbour.citizen.model;

import com.example.neighbour.flat.model.Flat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "citizens")
@SQLDelete(sql = "UPDATE citizens SET deleted_at = now() WHERE uid = ?")
@Where(clause = "deleted_at is null")
@Data
public class Citizen {
    @Id
    @GeneratedValue
    UUID uid;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flat_uid")
    private Flat flat;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}