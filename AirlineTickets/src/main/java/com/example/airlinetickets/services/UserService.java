package com.example.airlinetickets.services;


import com.example.airlinetickets.models.dtos.UserRegistrationDto;
import com.example.airlinetickets.models.entities.UserEntity;
import com.example.airlinetickets.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void registerUser(UserRegistrationDto registrationDto,
                             Consumer<Authentication> successfulLoginProcessor) {

        UserEntity userEntity = new UserEntity().
                setFirstName(registrationDto.getFirstName()).
                setLastName(registrationDto.getLastName()).
                setEmail(registrationDto.getEmail()).
                setUsername(registrationDto.getUsername()).
                setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(registrationDto.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
    }

    public boolean isEmailRegistered(UserRegistrationDto registrationDto) {

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(registrationDto.getEmail());
        return byEmail.isPresent();
    }

    public boolean isUsernameRegistered(UserRegistrationDto registrationDto) {

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(registrationDto.getUsername());
        return byUsername.isPresent();
    }
}
