package com.example.airlinetickets.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void flights() throws Exception {
        mockMvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(view().name("flights"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    void testAddFlightWithNotModeratorUserResultForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/flights/add"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN", "MODERATOR"})
    void testAddFlightWithModeratorUserCanAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/flights/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("flight-add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allAirplanes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allAirports"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("createFlightDto"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddFlightValidValuesFlightIsAdded() throws Exception {
        LocalDateTime departureDate = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivalDate = LocalDateTime.now().plusDays(2);

        mockMvc.perform(post("/flights/add")
                        .param("flightNumber", "RN143")
                        .param("departureDateTime", departureDate.toString())
                        .param("arrivalDateTime", arrivalDate.toString())
                        .param("originAirportId", "1")
                        .param("destinationAirportId", "5")
                        .param("airplaneId", "1")
                        .param("distance", "1")
                        .param("premiumTicketPrice", "1")
                        .param("businessTicketPrice", "1")
                        .param("economyTicketPrice", "1")
                        .param("premiumSeats", "1")
                        .param("businessSeats", "1")
                        .param("economySeats", "1")
                        .param("terminal", "T1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/flights"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddFlightValidValuesSecondTimeFlightIsNotAdded() throws Exception {
        LocalDateTime departureDate = LocalDateTime.now().plusDays(1);
        LocalDateTime arrivalDate = LocalDateTime.now().plusDays(2);

        mockMvc.perform(post("/flights/add")
                        .param("flightNumber", "RN943")
                        .param("departureDateTime", departureDate.toString())
                        .param("arrivalDateTime", arrivalDate.toString())
                        .param("originAirportId", "1")
                        .param("destinationAirportId", "5")
                        .param("airplaneId", "1")
                        .param("distance", "1")
                        .param("premiumTicketPrice", "1")
                        .param("businessTicketPrice", "1")
                        .param("economyTicketPrice", "1")
                        .param("premiumSeats", "1")
                        .param("businessSeats", "1")
                        .param("economySeats", "1")
                        .param("terminal", "T1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/flights"));

        mockMvc.perform(post("/flights/add")
                        .param("flightNumber", "RN843")
                        .param("departureDateTime", departureDate.toString())
                        .param("arrivalDateTime", arrivalDate.toString())
                        .param("originAirportId", "1")
                        .param("destinationAirportId", "5")
                        .param("airplaneId", "1")
                        .param("distance", "1")
                        .param("premiumTicketPrice", "1")
                        .param("businessTicketPrice", "1")
                        .param("economyTicketPrice", "1")
                        .param("premiumSeats", "1")
                        .param("businessSeats", "1")
                        .param("economySeats", "1")
                        .param("terminal", "T1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/flights"));
    }


    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddFlightWithInvalidValuesRedirectToFlightAdd() throws Exception {
        LocalDateTime departureDate = LocalDateTime.now().minusDays(1);
        LocalDateTime arrivalDate = LocalDateTime.now().minusDays(2);
        mockMvc.perform(post("/flights/add")
                        .param("flightNumber", "")
                        .param("departureDateTime", departureDate.toString())
                        .param("arrivalDateTime", arrivalDate.toString())
                        .param("originAirportId", "100")
                        .param("destinationAirportId", "100")
                        .param("airplaneId", "100")
                        .param("distance", "1")
                        .param("premiumTicketPrice", "1")
                        .param("businessTicketPrice", "1")
                        .param("economyTicketPrice", "1")
                        .param("premiumSeats", "1")
                        .param("businessSeats", "1")
                        .param("economySeats", "1")
                        .param("terminal", "T1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/flights/add"));
    }

}