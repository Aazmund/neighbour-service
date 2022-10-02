package com.example.neighbour.house.model;

import com.example.neighbour.street.model.Street;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Instant;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_uid")
    private Street street;

    @Column(name = "deleted_at")
    private Instant deletedAt;
}
