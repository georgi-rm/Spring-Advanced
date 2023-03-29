package com.example.airlinetickets.models.dtos.view;

import com.example.airlinetickets.models.enums.UserRoleEnum;

import java.util.List;

public class UserDetailsDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private List<UserRoleEnum> roles;

    public Long getId() {
        return id;
    }

    public UserDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserDetailsDto setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }
}
