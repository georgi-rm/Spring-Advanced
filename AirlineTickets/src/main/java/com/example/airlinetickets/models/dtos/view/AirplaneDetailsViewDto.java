package com.example.airlinetickets.models.dtos.view;

public class AirplaneDetailsViewDto {

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

    public AirplaneDetailsViewDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AirplaneDetailsViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMaximumSeats() {
        return maximumSeats;
    }

    public AirplaneDetailsViewDto setMaximumSeats(Integer maximumSeats) {
        this.maximumSeats = maximumSeats;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public AirplaneDetailsViewDto setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public AirplaneDetailsViewDto setWingspan(Double wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public AirplaneDetailsViewDto setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Integer getMaximumWeight() {
        return maximumWeight;
    }

    public AirplaneDetailsViewDto setMaximumWeight(Integer maximumWeight) {
        this.maximumWeight = maximumWeight;
        return this;
    }

    public Integer getMaximumCruisingSpeed() {
        return maximumCruisingSpeed;
    }

    public AirplaneDetailsViewDto setMaximumCruisingSpeed(Integer maximumCruisingSpeed) {
        this.maximumCruisingSpeed = maximumCruisingSpeed;
        return this;
    }

    public Integer getMaximumCruisingAltitude() {
        return maximumCruisingAltitude;
    }

    public AirplaneDetailsViewDto setMaximumCruisingAltitude(Integer maximumCruisingAltitude) {
        this.maximumCruisingAltitude = maximumCruisingAltitude;
        return this;
    }

    public Integer getRange() {
        return range;
    }

    public AirplaneDetailsViewDto setRange(Integer range) {
        this.range = range;
        return this;
    }

    public Integer getEngines() {
        return engines;
    }

    public AirplaneDetailsViewDto setEngines(Integer engines) {
        this.engines = engines;
        return this;
    }
}
