package com.example.airlinetickets.services;

import com.example.airlinetickets.models.dtos.CityDto;
import com.example.airlinetickets.models.dtos.binding.CreateCityDto;
import com.example.airlinetickets.models.entities.CityEntity;
import com.example.airlinetickets.repositories.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private final ModelMapper modelMapper;

    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public boolean create(CreateCityDto createCityDto) {
        Optional<CityEntity> cityFound = this.cityRepository.findByName(createCityDto.getName());

        if (cityFound.isPresent()) {
            return false;
        }

        CityEntity cityEntity = modelMapper.map(createCityDto, CityEntity.class);

        this.cityRepository.save(cityEntity);

        return true;
    }

    public List<CityDto> getAllCities() {
        return this.cityRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, CityDto.class))
                .toList();
    }
}
