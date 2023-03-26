package com.example.airlinetickets.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "city")
    private List<AirportEntity> airports;

    public City() {
    }

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public City setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<AirportEntity> getAirports() {
        return airports;
    }

    public City setAirports(List<AirportEntity> airports) {
        this.airports = airports;
        return this;
    }
}
