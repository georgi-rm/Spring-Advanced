package com.example.airlinetickets.models.dtos.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SearchFlightDto {

    @NotNull
    @Positive
    private Long originAirportId;

    @NotNull
    @Positive
    private Long destinationAirportId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    public Long getOriginAirportId() {
        return originAirportId;
    }

    public SearchFlightDto setOriginAirportId(Long originAirportId) {
        this.originAirportId = originAirportId;
        return this;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public SearchFlightDto setDestinationAirportId(Long destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
        return this;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public SearchFlightDto setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }
}
