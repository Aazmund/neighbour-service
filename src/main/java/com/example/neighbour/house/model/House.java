package com.example.neighbour.house.model;

import com.example.neighbour.flat.model.Flat;
import com.example.neighbour.street.model.Street;
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
@Table(name = "houses")
@SQLDelete(sql = "UPDATE houses SET deleted_at = now() WHERE uid = ?")
@Where(clause = "deleted_at is null")
@Data
public class House {
    @Id
    @GeneratedValue
    UUID uid;

    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street_uid")
    private Street street;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house")
    private List<Flat> flats;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
