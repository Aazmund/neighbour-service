package com.example.neighbour.street.repository;

import com.example.neighbour.street.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StreetRepository extends JpaRepository<Street, UUID> {
    Boolean existsByName(String name);
}
