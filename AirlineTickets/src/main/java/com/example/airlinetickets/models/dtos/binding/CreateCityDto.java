package com.example.airlinetickets.models.dtos.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCityDto {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    private String country;

    public String getName() {
        return name;
    }

    public CreateCityDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CreateCityDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
