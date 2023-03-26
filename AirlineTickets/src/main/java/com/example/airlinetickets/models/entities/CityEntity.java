package com.example.airlinetickets.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "city")
    private List<AirportEntity> airports;

    public CityEntity() {
    }

    public CityEntity(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public CityEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CityEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<AirportEntity> getAirports() {
        return airports;
    }

    public CityEntity setAirports(List<AirportEntity> airports) {
        this.airports = airports;
        return this;
    }
}
