package com.example.airlinetickets.models.dtos;

import com.example.airlinetickets.models.enums.UserRoleEnum;

import java.util.List;

public class UserRolesDto {

    private Long id;

    private String username;

    private List<UserRoleEnum> roles;

    public Long getId() {
        return id;
    }

    public UserRolesDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRolesDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserRolesDto setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }
}
