package com.example.neighbour.citizen.repository;

import com.example.neighbour.citizen.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, UUID> {

    Boolean existsByName(String name);

}
