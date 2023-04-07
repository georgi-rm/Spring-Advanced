package com.example.airlinetickets.models.mapper;

import com.example.airlinetickets.models.dtos.binding.CreateFlightDto;
import com.example.airlinetickets.models.dtos.view.FlightViewDto;
import com.example.airlinetickets.models.entities.FlightEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source = "originAirportEntity.name", target = "originAirport")
    @Mapping(source = "destinationAirportEntity.name", target = "destinationAirport")
    @Mapping(source = "airplaneEntity.model", target = "airplaneModel")
    FlightViewDto flightEntityToFlightViewDto(FlightEntity flightEntity);

    @Mapping(source = "destinationAirportId", target = "destinationAirportEntity.id")
    @Mapping(source = "originAirportId", target = "originAirportEntity.id")
    @Mapping(source = "airplaneId", target = "airplaneEntity.id")
    FlightEntity createFlightDtoToFlightEntity(CreateFlightDto createFlightDto);
}
