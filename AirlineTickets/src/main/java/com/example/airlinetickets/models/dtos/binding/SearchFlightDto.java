package com.example.airlinetickets.models.dtos.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SearchFlightDto {

    @NotNull
    @Positive
    private Long originAirportId;

    @NotNull
    @Positive
    private Long destinationAirportId;

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
}
