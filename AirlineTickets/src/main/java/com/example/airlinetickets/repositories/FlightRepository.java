package com.example.airlinetickets.repositories;

import com.example.airlinetickets.models.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    List<FlightEntity> findAllByOriginAirportEntityIdAndDestinationAirportEntityIdAndDepartureDateTimeBetween(Long originAirportId, Long destinationAirportId, LocalDateTime from, LocalDateTime to);
}
