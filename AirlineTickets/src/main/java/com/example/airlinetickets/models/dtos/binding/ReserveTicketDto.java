package com.example.airlinetickets.models.dtos.binding;

import com.example.airlinetickets.models.enums.SeatCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ReserveTicketDto {

    @NotNull
    private SeatCategory seatCategory;

    @NotBlank
    @Size(min = 1, max = 5)
    private String seatNumber;

    @NotNull
    @Positive
    private Long flightId;

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public ReserveTicketDto setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
        return this;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public ReserveTicketDto setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public Long getFlightId() {
        return flightId;
    }

    public ReserveTicketDto setFlightId(Long flightId) {
        this.flightId = flightId;
        return this;
    }
}
