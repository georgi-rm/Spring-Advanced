package com.example.airlinetickets.services;

import com.example.airlinetickets.repositories.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
