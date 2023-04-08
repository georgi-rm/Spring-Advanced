package com.example.airlinetickets.models.mapper;

import com.example.airlinetickets.models.dtos.binding.ReserveTicketDto;
import com.example.airlinetickets.models.dtos.view.TicketViewDto;
import com.example.airlinetickets.models.entities.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "user.lastName", target = "passengerName")
    @Mapping(source = "flight.originAirportEntity.name", target = "flight.originAirport")
    @Mapping(source = "flight.destinationAirportEntity.name", target = "flight.destinationAirport")
    @Mapping(source = "flight.airplaneEntity.model", target = "flight.airplaneModel")
    TicketViewDto flightEntityToFlightViewDto(TicketEntity ticketEntity);

    @Mapping(source = "flightId", target = "flight.id")
    TicketEntity reserveTicketDtoToTicketEntity(ReserveTicketDto reserveTicketDto);
}
