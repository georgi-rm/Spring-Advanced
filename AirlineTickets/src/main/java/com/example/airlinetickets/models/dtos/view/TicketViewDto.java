package com.example.airlinetickets.models.dtos.view;

import com.example.airlinetickets.models.enums.SeatCategory;

public class TicketViewDto {

    private Long id;

    private SeatCategory seatCategory;

    private String seatNumber;

    private String passengerName;

    private FlightViewDto flight;

    public Long getId() {
        return id;
    }

    public TicketViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public TicketViewDto setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
        return this;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public TicketViewDto setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public TicketViewDto setPassengerName(String passengerName) {
        this.passengerName = passengerName;
        return this;
    }

    public FlightViewDto getFlight() {
        return flight;
    }

    public TicketViewDto setFlight(FlightViewDto flight) {
        this.flight = flight;
        return this;
    }
}
