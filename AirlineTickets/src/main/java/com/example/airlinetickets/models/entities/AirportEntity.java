package com.example.airlinetickets.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "airports")
public class AirportEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String abbreviation;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

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

    public AirportEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public AirportEntity setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AirportEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AirportEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public CityEntity getCity() {
        return city;
    }

    public AirportEntity setCity(CityEntity city) {
        this.city = city;
        return this;
    }
}
