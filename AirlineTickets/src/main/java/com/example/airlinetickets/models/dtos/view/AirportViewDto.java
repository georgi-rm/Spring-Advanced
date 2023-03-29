package com.example.airlinetickets.models.dtos.view;

public class AirportViewDto {

    private Long id;

    private String name;

    private String abbreviation;

    private String imageUrl;

    private String description;

    private String city;

    private String country;

    public Long getId() {
        return id;
    }

    public AirportViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AirportViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public AirportViewDto setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AirportViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AirportViewDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AirportViewDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AirportViewDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
