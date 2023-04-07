package com.example.airlinetickets.exceptions;

public class FlightNotFoundException extends RuntimeException {

    private final Long flightId;

    public FlightNotFoundException(Long flightId) {

        super("Flight with ID: " + flightId + " not found !");

        this.flightId = flightId;
    }

    public Long getFlightId() {
        return flightId;
    }
}
