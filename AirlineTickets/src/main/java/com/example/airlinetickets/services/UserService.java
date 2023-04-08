package com.example.airlinetickets.services;

import com.example.airlinetickets.models.dtos.view.UserDetailsDto;
import com.example.airlinetickets.models.dtos.binding.UserRegistrationDto;
import com.example.airlinetickets.models.dtos.UserRolesDto;
import com.example.airlinetickets.models.entities.UserEntity;
import com.example.airlinetickets.models.entities.UserRoleEntity;
import com.example.airlinetickets.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final UserRoleService userRoleService;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService, UserRoleService userRoleService,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
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

    public void changeRoles(UserRolesDto userRolesDto) {

        Optional<UserEntity> foundUserOptional = this.userRepository.findById(userRolesDto.getId());

        if (foundUserOptional.isEmpty()) {
            return;
        }

        UserEntity foundUser = foundUserOptional.get();

        List<UserRoleEntity> userRoles = userRolesDto.getRoles().stream()
                .map(userRoleService::getRoleByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        foundUser.clearAllRoles();

        for (UserRoleEntity aUserRole : userRoles) {
            foundUser.addRole(aUserRole);
        }

        this.userRepository.save(foundUser);
    }

    public List<UserDetailsDto> getAllUsersDetails() {


        List<UserEntity> allUsers = this.userRepository.findAll();

        return allUsers.stream()
                .map(e -> new UserDetailsDto()
                        .setId(e.getId())
                        .setUsername(e.getUsername())
                        .setFirstName(e.getFirstName())
                        .setLastName(e.getLastName())
                        .setEmail(e.getEmail())
                        .setRoles(e.getRoles().stream().map(UserRoleEntity::getRole).toList())).toList();

//        PropertyMap<UserEntity, UserDetailsDto> roleMap = new PropertyMap<UserEntity, UserDetailsDto>() {
//            protected void configure() {
//                map().setRoles(source.getRoles().stream().map(UserRoleEntity::getRole).toList());
//            }
//        };
//
//        modelMapper.addMappings(roleMap);
//
//        return allUsers.stream().map(e -> modelMapper.map(e, UserDetailsDto.class)).toList();
    }

    public Optional<UserRolesDto> getUserRolesById(Long id) {

        return userRepository.findById(id).map(userEntity -> modelMapper.map(userEntity, UserRolesDto.class));

    }

    public UserEntity getUserEntityByUsername(String username) {

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
