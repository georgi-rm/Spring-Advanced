package com.example.airlinetickets.exceptions;

public class AirportInUseException extends RuntimeException {
    private final Long airportId;

    public AirportInUseException(Long airportId) {

        super("Airport with ID: " + airportId + " is used for flights !");

        this.airportId = airportId;
    }

    public Long getAirportId() {
        return airportId;
    }
}
