package com.example.airlinetickets.services;

import com.example.airlinetickets.models.dtos.binding.CreateFlightDto;
import com.example.airlinetickets.models.dtos.view.FlightViewDto;
import com.example.airlinetickets.models.entities.FlightEntity;
import com.example.airlinetickets.models.mapper.FlightMapper;
import com.example.airlinetickets.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightViewDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::flightEntityToFlightViewDto)
                .toList();
    }

    public void create(CreateFlightDto createFlightDto) {


        FlightEntity flightEntity = flightMapper.createFlightDtoToFlightEntity(createFlightDto);

        flightRepository.save(flightEntity);
    }

    public boolean hasFlightsFromToAirportWithId(Long airportId) {
        List<FlightEntity> flights = flightRepository.findAll()
                .stream()
                .filter(f -> Objects.equals(f.getOriginAirportEntity().getId(), airportId)
                        || Objects.equals(f.getDestinationAirportEntity().getId(), airportId))
                .toList();

        return !flights.isEmpty();
    }
}
