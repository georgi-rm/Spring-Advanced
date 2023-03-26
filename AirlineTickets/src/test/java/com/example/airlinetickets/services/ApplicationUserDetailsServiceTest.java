package com.example.airlinetickets.services;

import com.example.airlinetickets.models.entities.UserEntity;
import com.example.airlinetickets.models.entities.UserRoleEntity;
import com.example.airlinetickets.models.enums.UserRoleEnum;
import com.example.airlinetickets.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {


    private final String NOT_EXISTING_USERNAME = "pesho";

    private ApplicationUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserFound() {

        // ARRANGE
        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testModeratorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);

        String EXISTING_USERNAME = "george";
        UserEntity testUserEntity = new UserEntity().
                setUsername(EXISTING_USERNAME).
                setEmail("test@example.com").
                setPassword("topSecret").
                setRoles(List.of(testAdminRole, testModeratorRole));


        when(mockUserRepository.findByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testUserEntity));


        // ACT
        UserDetails adminDetails = toTest.loadUserByUsername(EXISTING_USERNAME);

        // ASSERT
        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_USERNAME, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(2,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just two - ADMIN/MODERATOR.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_MODERATOR");
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities, String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }


    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USERNAME)
        );
    }

}
