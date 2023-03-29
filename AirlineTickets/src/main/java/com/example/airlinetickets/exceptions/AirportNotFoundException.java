package com.example.airlinetickets.exceptions;

public class AirportNotFoundException extends RuntimeException {

    private final Long airportId;

    public AirportNotFoundException(Long airportId) {

        super("Airport with ID: " + airportId + " not found !");

        this.airportId = airportId;
    }

    public Long getAirportId() {
        return airportId;
    }
}
