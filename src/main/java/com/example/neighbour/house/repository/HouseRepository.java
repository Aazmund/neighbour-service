package com.example.neighbour.house.repository;

import com.example.neighbour.house.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<House, UUID> {
    Boolean existsByNumber(String number);
}
