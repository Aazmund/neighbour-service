package com.example.neighbour.flat.repository;

import com.example.neighbour.flat.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FlatRepository extends JpaRepository<Flat, UUID> {
    Boolean existsByNumber(String number);
}
