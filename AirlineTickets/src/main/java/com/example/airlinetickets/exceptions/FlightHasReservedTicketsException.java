package com.example.airlinetickets.exceptions;

public class FlightHasReservedTicketsException extends RuntimeException {

    private final Long flightId;

    public FlightHasReservedTicketsException(Long flightId) {

        super("Airport with ID: " + flightId + " has reserved tickets !");

        this.flightId = flightId;
    }

    public Long getFlightId() {
        return flightId;
    }
}
