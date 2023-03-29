package com.example.airlinetickets.models.dtos;

public class CityDto {

    private Long id;

    private String name;

    private String country;

    public Long getId() {
        return id;
    }

    public CityDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CityDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
