package com.example.airlinetickets.repositories;

import com.example.airlinetickets.models.entities.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long> {
    Optional<AirportEntity> findByName(String name);

    Optional<AirportEntity> findByAbbreviation(String abbreviation);
}
