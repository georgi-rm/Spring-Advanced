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
public class AirplaneControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void testGetAirplanesWithAnonymousUserGetSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airplanes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("airplanes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createAirplaneDto"));

    }

    @Test
    @WithAnonymousUser
    void testGetAirplanesDetailsWithAnonymousUserGetSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airplanes/details?airplaneId=1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("airplane-details"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createAirplaneDto"));

    }

    @Test
    @WithAnonymousUser
    void testAddAirplaneWithAnonymousUserRedirectToLogIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airplanes/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    void testAddAirplaneWithNotModeratorUserResultForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/airplanes/add"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN", "MODERATOR"})
    void testAddAirplaneWithModeratorUserCanAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/airplanes/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("airplane-add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createAirplaneDto"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddAirplaneValidValuesAirplaneIsAdded() throws Exception {
        mockMvc.perform(post("/airplanes/add")
                        .param("model", "Boeing 747-600")
                        .param("imageUrl", "imageUrl")
                        .param("maximumSeats", "500")
                        .param("length", "70")
                        .param("wingspan", "76")
                        .param("height", "16")
                        .param("maximumWeight", "890")
                        .param("maximumCruisingSpeed", "900")
                        .param("maximumCruisingAltitude", "13405")
                        .param("range", "10200")
                        .param("engines", "4")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airplanes"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddAirplaneValidValuesSecondTimeAirplaneIsNotAdded() throws Exception {
        mockMvc.perform(post("/airplanes/add")
                        .param("model", "Boeing 747-800")
                        .param("imageUrl", "imageUrl")
                        .param("maximumSeats", "500")
                        .param("length", "70")
                        .param("wingspan", "76")
                        .param("height", "16")
                        .param("maximumWeight", "890")
                        .param("maximumCruisingSpeed", "900")
                        .param("maximumCruisingAltitude", "13405")
                        .param("range", "10200")
                        .param("engines", "4")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airplanes"));

        mockMvc.perform(post("/airplanes/add")
                        .param("model", "Boeing 747-800")
                        .param("imageUrl", "imageUrl")
                        .param("maximumSeats", "500")
                        .param("length", "70")
                        .param("wingspan", "76")
                        .param("height", "16")
                        .param("maximumWeight", "890")
                        .param("maximumCruisingSpeed", "900")
                        .param("maximumCruisingAltitude", "13405")
                        .param("range", "10200")
                        .param("engines", "4")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airplanes/add"));
    }


    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddAirplaneWithInvalidValuesRedirectToAirplaneAdd() throws Exception {
        mockMvc.perform(post("/airplanes/add")
                        .param("model", "")
                        .param("imageUrl", "imageUrl")
                        .param("maximumSeats", "500")
                        .param("length", "70")
                        .param("wingspan", "76")
                        .param("height", "16")
                        .param("maximumWeight", "890")
                        .param("maximumCruisingSpeed", "900")
                        .param("maximumCruisingAltitude", "13405")
                        .param("range", "10200")
                        .param("engines", "4")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/airplanes/add"));
    }
}
