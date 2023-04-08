package com.example.airlinetickets.utilities;

import com.example.airlinetickets.models.entities.FlightEntity;
import com.example.airlinetickets.services.FlightService;
import com.example.airlinetickets.services.TicketService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClearPassedFlightsScheduler {

    private final FlightService flightService;

    private final TicketService ticketService;

    public ClearPassedFlightsScheduler(FlightService flightService, TicketService ticketService) {
        this.flightService = flightService;
        this.ticketService = ticketService;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void clearPassedFlightsEveryHour() {

        List<FlightEntity> allFlights = flightService.getAllPassedFlights();

        for (FlightEntity flight: allFlights) {
            ticketService.deleteAllTicketsByFlightId(flight.getId());
            flightService.deleteById(flight.getId());
        }
    }
}
