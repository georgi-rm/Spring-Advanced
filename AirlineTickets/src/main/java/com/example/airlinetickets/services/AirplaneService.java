package com.example.airlinetickets.services;

import com.example.airlinetickets.models.dtos.AirplaneDetailsDto;
import com.example.airlinetickets.models.dtos.AirplaneViewDto;
import com.example.airlinetickets.models.dtos.CreateAirplaneDto;
import com.example.airlinetickets.models.entities.AirplaneEntity;
import com.example.airlinetickets.repositories.AirplaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public boolean create(CreateAirplaneDto createAirplaneDto) {

        Optional<AirplaneEntity> byModel =
                this.airplaneRepository.findByModel(createAirplaneDto.getModel());

        if (byModel.isPresent()) {
            return false;
        }

        ModelMapper modelMapper = new ModelMapper();

        AirplaneEntity airplaneEntity = modelMapper.map(createAirplaneDto, AirplaneEntity.class);

        this.airplaneRepository.save(airplaneEntity);

        return true;
    }

    public Optional<AirplaneDetailsDto> findAirplaneById(Long airplaneId) {
        ModelMapper modelMapper = new ModelMapper();

        Optional<AirplaneEntity> airplane = this.airplaneRepository.findById(airplaneId);

        if (airplane.isEmpty()) {
            return Optional.empty();
        }

        AirplaneDetailsDto airplaneDetailsDto = modelMapper.map(airplane.get(), AirplaneDetailsDto.class);

        return Optional.of(airplaneDetailsDto);
    }

    public List<AirplaneViewDto> getAllAirplanes() {

        ModelMapper modelMapper = new ModelMapper();

        List<AirplaneEntity> allAirplanes= this.airplaneRepository.findAll();

        return allAirplanes.stream().map(e -> modelMapper.map(e, AirplaneViewDto.class)).toList();
    }
}
