package com.example.airlinetickets.models.dtos.binding;

import jakarta.validation.constraints.*;

public class CreateAirplaneDto {
    @NotBlank
    @Size(min = 2, max = 50)
    private String model;

    @NotBlank
    private String imageUrl;

    @NotNull
    @Positive
    private Integer maximumSeats;

    @NotNull
    @Positive
    private Double length;

    @NotNull
    @Positive
    private Double wingspan;

    @NotNull
    @Positive
    private Double height;

    @NotNull
    @Positive
    private Integer maximumWeight;

    @NotNull
    @Positive
    private Integer maximumCruisingSpeed;

    @NotNull
    @Positive
    private Integer maximumCruisingAltitude;

    @NotNull
    @Positive
    private Integer range;

    @NotNull
    @Positive
    private Integer engines;

    public String getModel() {
        return model;
    }

    public CreateAirplaneDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateAirplaneDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMaximumSeats() {
        return maximumSeats;
    }

    public CreateAirplaneDto setMaximumSeats(Integer maximumSeats) {
        this.maximumSeats = maximumSeats;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public CreateAirplaneDto setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public CreateAirplaneDto setWingspan(Double wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public CreateAirplaneDto setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Integer getMaximumWeight() {
        return maximumWeight;
    }

    public CreateAirplaneDto setMaximumWeight(Integer maximumWeight) {
        this.maximumWeight = maximumWeight;
        return this;
    }

    public Integer getMaximumCruisingSpeed() {
        return maximumCruisingSpeed;
    }

    public CreateAirplaneDto setMaximumCruisingSpeed(Integer maximumCruisingSpeed) {
        this.maximumCruisingSpeed = maximumCruisingSpeed;
        return this;
    }

    public Integer getMaximumCruisingAltitude() {
        return maximumCruisingAltitude;
    }

    public CreateAirplaneDto setMaximumCruisingAltitude(Integer maximumCruisingAltitude) {
        this.maximumCruisingAltitude = maximumCruisingAltitude;
        return this;
    }

    public Integer getRange() {
        return range;
    }

    public CreateAirplaneDto setRange(Integer range) {
        this.range = range;
        return this;
    }

    public Integer getEngines() {
        return engines;
    }

    public CreateAirplaneDto setEngines(Integer engines) {
        this.engines = engines;
        return this;
    }
}
