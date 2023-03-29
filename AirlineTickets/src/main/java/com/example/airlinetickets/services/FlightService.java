package com.example.airlinetickets.services;

import com.example.airlinetickets.models.entities.FlightEntity;
import com.example.airlinetickets.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    public boolean hasFlightsFromToAirportWithId(Long airportId) {
        List<FlightEntity> flights = flightRepository.findAll()
                .stream()
                .filter(f -> Objects.equals(f.getOriginAirport().getId(), airportId)
                        || Objects.equals(f.getDestinationAirport().getId(), airportId))
                .toList();

        return flights.size() > 0;
    }
}
