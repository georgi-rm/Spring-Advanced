package com.example.airlinetickets.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {
    @NotBlank
    @Size(min = 3, max = 10)
    private String username;

    @NotBlank
    @Size(min = 3)
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
