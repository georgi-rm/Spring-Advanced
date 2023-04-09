package com.example.airlinetickets.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AirplaneRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "admin", roles = {"MODERATOR", "ADMIN"})
    void testFetchAllItemsSuccess() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/airplanes/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].model").value("Boeing 747-400"))
                .andExpect(jsonPath("[0].imageUrl").value("https://www.lufthansa.com/content/dam/lh/images/local_images/discover-lufthansa/fleet/B747-400_1144x490.jpg"));
    }

    @Test
    @WithMockUser(value = "admin", roles = {"MODERATOR", "ADMIN"})
    void testFetchExistingItemSuccess() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/airplanes/details/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Boeing 747-400"))
                .andExpect(jsonPath("$.imageUrl").value("https://www.lufthansa.com/content/dam/lh/images/local_images/discover-lufthansa/fleet/B747-400_1144x490.jpg"));
    }

    @Test
    @WithMockUser(value = "admin", roles = {"MODERATOR", "ADMIN"})
    void testFetchNotExistingItemNotFound() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/airplanes/details/19999"))
                .andExpect(status().is4xxClientError());
    }
}
