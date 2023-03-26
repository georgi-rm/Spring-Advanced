package com.example.airlinetickets.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "airplanes")
public class AirplaneEntity extends BaseEntity{
    @Column(nullable = false)
    private String model;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "maximum_seats", nullable = false)
    private Integer maximumSeats;

    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private Double wingspan;

    @Column(nullable = false)
    private Double height;

    @Column(name = "maximum_weight", nullable = false)
    private Integer maximumWeight;

    @Column(name = "maximum_cruising_speed", nullable = false)
    private Integer maximumCruisingSpeed;

    @Column(name = "maximum_cruising_altitude", nullable = false)
    private Integer maximumCruisingAltitude;

    @Column(name = "`range`", nullable = false)
    private Integer range;

    @Column(nullable = false)
    private Integer engines;

    public AirplaneEntity() {
    }

    public AirplaneEntity(String model, String imageUrl, Integer maximumSeats, Double length, Double wingspan, Double height, Integer maximumWeight, Integer maximumCruisingSpeed, Integer maximumCruisingAltitude, Integer range, Integer engines) {
        this.model = model;
        this.imageUrl = imageUrl;
        this.maximumSeats = maximumSeats;
        this.length = length;
        this.wingspan = wingspan;
        this.height = height;
        this.maximumWeight = maximumWeight;
        this.maximumCruisingSpeed = maximumCruisingSpeed;
        this.maximumCruisingAltitude = maximumCruisingAltitude;
        this.range = range;
        this.engines = engines;
    }

    public String getModel() {
        return model;
    }

    public AirplaneEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AirplaneEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMaximumSeats() {
        return maximumSeats;
    }

    public AirplaneEntity setMaximumSeats(Integer maximumSeats) {
        this.maximumSeats = maximumSeats;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public AirplaneEntity setLength(Double length) {
        this.length = length;
        return this;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public AirplaneEntity setWingspan(Double wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public AirplaneEntity setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Integer getMaximumWeight() {
        return maximumWeight;
    }

    public AirplaneEntity setMaximumWeight(Integer maximumWeight) {
        this.maximumWeight = maximumWeight;
        return this;
    }

    public Integer getMaximumCruisingSpeed() {
        return maximumCruisingSpeed;
    }

    public AirplaneEntity setMaximumCruisingSpeed(Integer maximumCruisingSpeed) {
        this.maximumCruisingSpeed = maximumCruisingSpeed;
        return this;
    }

    public Integer getMaximumCruisingAltitude() {
        return maximumCruisingAltitude;
    }

    public AirplaneEntity setMaximumCruisingAltitude(Integer maximumCruisingAltitude) {
        this.maximumCruisingAltitude = maximumCruisingAltitude;
        return this;
    }

    public Integer getRange() {
        return range;
    }

    public AirplaneEntity setRange(Integer range) {
        this.range = range;
        return this;
    }

    public Integer getEngines() {
        return engines;
    }

    public AirplaneEntity setEngines(Integer engines) {
        this.engines = engines;
        return this;
    }
}
