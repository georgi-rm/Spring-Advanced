package com.example.airlinetickets.models.entities;

import com.example.airlinetickets.models.enums.SeatCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_category", nullable = false)
    private SeatCategory seatCategory;

    @Column(name = "seat_number", unique = true, nullable = false)
    private String seatNumber;

    @ManyToOne
    private FlightEntity flight;

    @ManyToOne
    private UserEntity user;

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public TicketEntity setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
        return this;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public TicketEntity setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public TicketEntity setFlight(FlightEntity flight) {
        this.flight = flight;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public TicketEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
