package com.example.airlinetickets.models.dtos.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateAirportDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3, max = 3)
    private String abbreviation;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String description;

    @NotNull
    private Long cityId;

    public String getName() {
        return name;
    }

    public CreateAirportDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public CreateAirportDto setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateAirportDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateAirportDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public CreateAirportDto setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }
}
