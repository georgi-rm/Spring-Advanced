package com.example.airlinetickets.models.mapper;

import com.example.airlinetickets.models.dtos.binding.CreateAirportDto;
import com.example.airlinetickets.models.dtos.view.AirportViewDto;
import com.example.airlinetickets.models.entities.AirportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    @Mapping(source = "city.name", target = "city")
    @Mapping(source = "city.country", target = "country")
    AirportViewDto airportEntityToAirportViewDto(AirportEntity airportEntity);

    @Mapping(source = "cityId", target = "city.id")
    AirportEntity createAirportDtoToAirportEntity(CreateAirportDto createAirportDto);
}
