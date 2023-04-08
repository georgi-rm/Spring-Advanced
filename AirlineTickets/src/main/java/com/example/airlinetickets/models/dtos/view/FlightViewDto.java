package com.example.airlinetickets.models.dtos.view;

import java.time.LocalDateTime;

public class FlightViewDto {

    private Long id;

    private String flightNumber;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    private String originAirport;

    private String destinationAirport;

    private String airplaneModel;

    public Long getId() {
        return id;
    }

    public FlightViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public FlightViewDto setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public FlightViewDto setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        return this;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public FlightViewDto setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        return this;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public FlightViewDto setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
        return this;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public FlightViewDto setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
        return this;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public FlightViewDto setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
        return this;
    }
}
