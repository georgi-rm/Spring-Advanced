package com.example.airlinetickets.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class FlightEntity extends BaseEntity {

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalDateTime;

    @ManyToOne
    private AirportEntity originAirportEntity;

    @ManyToOne
    private AirportEntity destinationAirportEntity;

    @ManyToOne
    private AirplaneEntity airplaneEntity;

    @Column(nullable = false)
    private Integer distance;

    @Column(name = "premium_ticket_price")
    private BigDecimal premiumTicketPrice;

    @Column(name = "business_ticket_price")
    private BigDecimal businessTicketPrice;

    @Column(name = "economy_ticket_price")
    private BigDecimal economyTicketPrice;

    @Column(name = "premium_seats", nullable = false)
    private Integer premiumSeats;

    @Column(name = "business_seats", nullable = false)
    private Integer businessSeats;

    @Column(name = "economy_seats", nullable = false)
    private Integer economySeats;

    private String terminal;

    public FlightEntity() {
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public FlightEntity setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public FlightEntity setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        return this;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public FlightEntity setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        return this;
    }

    public AirportEntity getOriginAirportEntity() {
        return originAirportEntity;
    }

    public FlightEntity setOriginAirportEntity(AirportEntity originAirportEntity) {
        this.originAirportEntity = originAirportEntity;
        return this;
    }

    public AirportEntity getDestinationAirportEntity() {
        return destinationAirportEntity;
    }

    public FlightEntity setDestinationAirportEntity(AirportEntity destinationAirportEntity) {
        this.destinationAirportEntity = destinationAirportEntity;
        return this;
    }

    public AirplaneEntity getAirplaneEntity() {
        return airplaneEntity;
    }

    public FlightEntity setAirplaneEntity(AirplaneEntity airplaneEntity) {
        this.airplaneEntity = airplaneEntity;
        return this;
    }

    public Integer getDistance() {
        return distance;
    }

    public FlightEntity setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public BigDecimal getPremiumTicketPrice() {
        return premiumTicketPrice;
    }

    public FlightEntity setPremiumTicketPrice(BigDecimal premiumTicketPrice) {
        this.premiumTicketPrice = premiumTicketPrice;
        return this;
    }

    public BigDecimal getBusinessTicketPrice() {
        return businessTicketPrice;
    }

    public FlightEntity setBusinessTicketPrice(BigDecimal businessTicketPrice) {
        this.businessTicketPrice = businessTicketPrice;
        return this;
    }

    public BigDecimal getEconomyTicketPrice() {
        return economyTicketPrice;
    }

    public FlightEntity setEconomyTicketPrice(BigDecimal economyTicketPrice) {
        this.economyTicketPrice = economyTicketPrice;
        return this;
    }

    public Integer getPremiumSeats() {
        return premiumSeats;
    }

    public FlightEntity setPremiumSeats(Integer premiumSeats) {
        this.premiumSeats = premiumSeats;
        return this;
    }

    public Integer getBusinessSeats() {
        return businessSeats;
    }

    public FlightEntity setBusinessSeats(Integer businessSeats) {
        this.businessSeats = businessSeats;
        return this;
    }

    public Integer getEconomySeats() {
        return economySeats;
    }

    public FlightEntity setEconomySeats(Integer economySeats) {
        this.economySeats = economySeats;
        return this;
    }

    public String getTerminal() {
        return terminal;
    }

    public FlightEntity setTerminal(String terminal) {
        this.terminal = terminal;
        return this;
    }
}
