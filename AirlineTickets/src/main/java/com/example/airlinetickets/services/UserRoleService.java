package com.example.airlinetickets.services;


import com.example.airlinetickets.models.entities.UserRoleEntity;
import com.example.airlinetickets.models.enums.UserRoleEnum;
import com.example.airlinetickets.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepositoryRepository;

    public UserRoleService(UserRoleRepository userRoleRepositoryRepository) {
        this.userRoleRepositoryRepository = userRoleRepositoryRepository;
    }


    public Optional<UserRoleEntity> getRoleByName(UserRoleEnum role) {

        return userRoleRepositoryRepository.findByRole(role);
    }
}
