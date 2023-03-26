package com.example.airlinetickets.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "airports")
public class AirportEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviation;

    @ManyToOne()
    private CityEntity city;

    public AirportEntity() {
    }

    public AirportEntity(String name, String abbreviation, CityEntity city) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public CityEntity getCity() {
        return city;
    }
}