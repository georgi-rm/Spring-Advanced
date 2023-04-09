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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CityControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void testAddCityWithAnonymousUserRedirectToLogIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cities/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    void testAddCityWithNotModeratorUserResultForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cities/add"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN", "MODERATOR"})
    void testAddCityWithModeratorUserCanAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/cities/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("city-add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createCityDto"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddCityValidValuesCityIsAdded() throws Exception {
        mockMvc.perform(post("/cities/add")
                        .param("name", "Varna")
                        .param("country", "Bulgaria")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddCityValidValuesSecondTimeCityIsNotAdded() throws Exception {
        mockMvc.perform(post("/cities/add")
                        .param("name", "Burgas")
                        .param("country", "Bulgaria")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(post("/cities/add")
                        .param("name", "Burgas")
                        .param("country", "Bulgaria")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cities/add"));
    }


    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddCityWithInvalidValuesRedirectToCityAdd() throws Exception {
        mockMvc.perform(post("/cities/add")
                        .param("name", "")
                        .param("country", "")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cities/add"));
    }
}
