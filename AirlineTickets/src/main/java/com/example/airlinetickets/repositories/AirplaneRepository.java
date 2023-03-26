package com.example.airlinetickets.repositories;

import com.example.airlinetickets.models.entities.AirplaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<AirplaneEntity, Long> {

    Optional<AirplaneEntity> findByModel(String model);

    Optional<AirplaneEntity> findById(Long id);
}
