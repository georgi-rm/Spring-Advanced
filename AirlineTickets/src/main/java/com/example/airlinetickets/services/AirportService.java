package com.example.airlinetickets.services;

import com.example.airlinetickets.models.entities.AirportEntity;
import com.example.airlinetickets.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    public List<AirportEntity> getAllAirports() {
        return airportRepository.findAll();
    }
}
