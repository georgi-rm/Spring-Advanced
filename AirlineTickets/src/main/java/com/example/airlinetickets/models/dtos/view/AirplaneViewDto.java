package com.example.airlinetickets.models.dtos.view;

public class AirplaneViewDto {

    private Long id;

    private String model;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public AirplaneViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public AirplaneViewDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AirplaneViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
