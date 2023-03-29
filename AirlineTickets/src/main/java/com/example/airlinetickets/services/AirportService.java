package com.example.airlinetickets.services;

import com.example.airlinetickets.exceptions.AirportNotFoundException;
import com.example.airlinetickets.models.dtos.binding.CreateAirportDto;
import com.example.airlinetickets.models.dtos.view.AirportViewDto;
import com.example.airlinetickets.models.entities.AirportEntity;
import com.example.airlinetickets.models.mapper.AirportMapper;
import com.example.airlinetickets.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

    public AirportService(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    public boolean create(CreateAirportDto createAirportDto) {

        if (isNameUnavailable(createAirportDto.getName())
                || isAbbreviationUnavailable(createAirportDto.getAbbreviation())) {
            return false;
        }

        AirportEntity airportEntity = airportMapper.createAirportDtoToAirportEntity(createAirportDto);

        airportRepository.save(airportEntity);

        return true;
    }

    public List<AirportViewDto> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(airportMapper::airportEntityToAirportViewDto)
                .toList();
    }

    public AirportViewDto getAirportById(Long id) {

        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) {
            throw new AirportNotFoundException(id);
        }

        return airportMapper.airportEntityToAirportViewDto(airportEntity.get());
    }

    public boolean isNameUnavailable(String name) {
        Optional<AirportEntity> airportByName = airportRepository.findByName(name);

        return airportByName.isPresent();
    }


    public boolean isAbbreviationUnavailable(String abbreviation) {
        Optional<AirportEntity> airportByAbbreviation = airportRepository.findByAbbreviation(abbreviation);

        return airportByAbbreviation.isPresent();
    }

    public void deleteAirportById(Long id) {

        airportRepository.deleteById(id);
    }
}
