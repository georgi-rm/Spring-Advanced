package com.example.airlinetickets.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AirportControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void testGetAirportsWithAnonymousUserGetSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airports"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("airports"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allAirports"));

    }

    @Test
    @WithAnonymousUser
    void testGetAirportsDetailsWithAnonymousUserGetSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airports/details/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("airport-details"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("airport"));

    }

    @Test
    @WithAnonymousUser
    void testGetNotExistingAirportsDetailsWithAnonymousUserThrowException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airports/details/100"))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.view().name("errors/airport-not-found"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("airportId"));

    }

    @Test
    @WithAnonymousUser
    void testAddAirportWithAnonymousUserRedirectToLogIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airports/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    void testAddAirportWithNotModeratorUserResultForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airports/add"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN", "MODERATOR"})
    void testAddAirportWithModeratorUserCanAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/airports/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("airport-add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createAirportDto"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddAirportValidValuesAirportIsAdded() throws Exception {
        mockMvc.perform(post("/airports/add")
                        .param("name", "Madrid–Barajas Airport")
                        .param("abbreviation", "MAD")
                        .param("imageUrl", "imageUrl")
                        .param("description", "description")
                        .param("cityId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airports"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddAirportValidValuesSecondTimeAirportIsNotAdded() throws Exception {
        mockMvc.perform(post("/airports/add")
                        .param("name", "Miami International Airport")
                        .param("abbreviation", "MIA")
                        .param("imageUrl", "imageUrl")
                        .param("description", "description")
                        .param("cityId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airports"));

        mockMvc.perform(post("/airports/add")
                        .param("name", "Miami International Airport")
                        .param("abbreviation", "MIA")
                        .param("imageUrl", "imageUrl")
                        .param("description", "description")
                        .param("cityId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airports/add"));
    }


    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddAirportWithInvalidValuesRedirectToAirportAdd() throws Exception {
        mockMvc.perform(post("/airports/add")
                        .param("name", "")
                        .param("abbreviation", "MIA")
                        .param("imageUrl", "imageUrl")
                        .param("description", "description")
                        .param("cityId", "100")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airports/add"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testDeleteAirportThatCanNotBeDeleted() throws Exception {
        mockMvc.perform(delete("/airports/1")
                        .with(csrf())
                )
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.view().name("errors/airport-in-use"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("airportId"));
    }
}
