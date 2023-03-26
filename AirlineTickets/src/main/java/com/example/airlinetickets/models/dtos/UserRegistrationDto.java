package com.example.airlinetickets.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {

    @Size(min = 3, max = 20)
    @NotBlank
    private String username;

    @Size(min = 2, max = 50)
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 50)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Size(min = 3)
    @NotBlank
    private String password;

    @Size(min = 3)
    @NotBlank
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegistrationDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
