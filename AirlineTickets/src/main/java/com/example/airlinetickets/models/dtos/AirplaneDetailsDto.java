package com.example.airlinetickets.models.dtos;

public class AirplaneDetailsDto {

    private String model;

    private String imageUrl;

    private Integer maximumSeats;

    private Double length;

    private Double wingspan;

    private Double height;

    private Integer maximumWeight;

    private Integer maximumCruisingSpeed;

    private Integer maximumCruisingAltitude;

    private Integer range;

    private Integer engines;

    public String getModel() {
        return model;
    }

    public AirplaneDetailsDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AirplaneDetailsDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMaximumSeats() {
        return maximumSeats;
    }

    public AirplaneDetailsDto setMaximumSeats(Integer maximumSeats) {
        this.maximumSeats = maximumSeats;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public AirplaneDetailsDto setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public AirplaneDetailsDto setWingspan(Double wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public AirplaneDetailsDto setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Integer getMaximumWeight() {
        return maximumWeight;
    }

    public AirplaneDetailsDto setMaximumWeight(Integer maximumWeight) {
        this.maximumWeight = maximumWeight;
        return this;
    }

    public Integer getMaximumCruisingSpeed() {
        return maximumCruisingSpeed;
    }

    public AirplaneDetailsDto setMaximumCruisingSpeed(Integer maximumCruisingSpeed) {
        this.maximumCruisingSpeed = maximumCruisingSpeed;
        return this;
    }

    public Integer getMaximumCruisingAltitude() {
        return maximumCruisingAltitude;
    }

    public AirplaneDetailsDto setMaximumCruisingAltitude(Integer maximumCruisingAltitude) {
        this.maximumCruisingAltitude = maximumCruisingAltitude;
        return this;
    }

    public Integer getRange() {
        return range;
    }

    public AirplaneDetailsDto setRange(Integer range) {
        this.range = range;
        return this;
    }

    public Integer getEngines() {
        return engines;
    }

    public AirplaneDetailsDto setEngines(Integer engines) {
        this.engines = engines;
        return this;
    }
}
