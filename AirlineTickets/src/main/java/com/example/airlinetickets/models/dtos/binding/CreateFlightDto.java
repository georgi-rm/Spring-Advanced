package com.example.airlinetickets.models.dtos.binding;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateFlightDto {


    @NotBlank
    @Size(min = 3, max = 10)
    private String flightNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime departureDateTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arrivalDateTime;

    @NotNull
    private Long originAirportId;

    @NotNull
    private Long destinationAirportId;

    @NotNull
    private Long airplaneId;

    @NotNull
    @Positive
    private Integer distance;

    @NotNull
    @PositiveOrZero
    private BigDecimal premiumTicketPrice;

    @NotNull
    @PositiveOrZero
    private BigDecimal businessTicketPrice;

    @NotNull
    @PositiveOrZero
    private BigDecimal economyTicketPrice;

    @NotNull
    @PositiveOrZero
    private Integer premiumSeats;

    @NotNull
    @PositiveOrZero
    private Integer businessSeats;

    @NotNull
    @PositiveOrZero
    private Integer economySeats;

    @NotBlank
    @Size(min = 1, max = 10)
    private String terminal;

    public String getFlightNumber() {
        return flightNumber;
    }

    public CreateFlightDto setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public CreateFlightDto setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        return this;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public CreateFlightDto setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        return this;
    }

    public Long getOriginAirportId() {
        return originAirportId;
    }

    public CreateFlightDto setOriginAirportId(Long originAirportId) {
        this.originAirportId = originAirportId;
        return this;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public CreateFlightDto setDestinationAirportId(Long destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
        return this;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public CreateFlightDto setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
        return this;
    }

    public Integer getDistance() {
        return distance;
    }

    public CreateFlightDto setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public BigDecimal getPremiumTicketPrice() {
        return premiumTicketPrice;
    }

    public CreateFlightDto setPremiumTicketPrice(BigDecimal premiumTicketPrice) {
        this.premiumTicketPrice = premiumTicketPrice;
        return this;
    }

    public BigDecimal getBusinessTicketPrice() {
        return businessTicketPrice;
    }

    public CreateFlightDto setBusinessTicketPrice(BigDecimal businessTicketPrice) {
        this.businessTicketPrice = businessTicketPrice;
        return this;
    }

    public BigDecimal getEconomyTicketPrice() {
        return economyTicketPrice;
    }

    public CreateFlightDto setEconomyTicketPrice(BigDecimal economyTicketPrice) {
        this.economyTicketPrice = economyTicketPrice;
        return this;
    }

    public Integer getPremiumSeats() {
        return premiumSeats;
    }

    public CreateFlightDto setPremiumSeats(Integer premiumSeats) {
        this.premiumSeats = premiumSeats;
        return this;
    }

    public Integer getBusinessSeats() {
        return businessSeats;
    }

    public CreateFlightDto setBusinessSeats(Integer businessSeats) {
        this.businessSeats = businessSeats;
        return this;
    }

    public Integer getEconomySeats() {
        return economySeats;
    }

    public CreateFlightDto setEconomySeats(Integer economySeats) {
        this.economySeats = economySeats;
        return this;
    }

    public String getTerminal() {
        return terminal;
    }

    public CreateFlightDto setTerminal(String terminal) {
        this.terminal = terminal;
        return this;
    }
}
