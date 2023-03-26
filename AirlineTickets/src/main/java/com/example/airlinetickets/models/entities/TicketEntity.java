package com.example.airlinetickets.models.entities;

import com.example.airlinetickets.models.enums.SeatCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_category", nullable = false)
    private SeatCategory seatCategory;

    @Column(name = "seat_number", unique = true, nullable = false)
    private String seatNumber;

    @ManyToOne
    private FlightEntity flight;

    @ManyToOne
    private UserEntity user;

}
