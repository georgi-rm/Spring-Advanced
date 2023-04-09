package com.example.airlinetickets.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "george")
    void tickets() throws Exception {
        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ticketViewDtoList"));

    }

    @Test
    @WithMockUser(value = "george")
    void testAddTicketsWithNotModeratorUserResultForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tickets/reserve/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ticket-reserve"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("reserveTicketDto"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN", "MODERATOR"})
    void testAddTicketsWithModeratorUserCanAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/tickets/reserve/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ticket-reserve"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("reserveTicketDto"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("flightData"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddTicketValidValuesTicketIsAdded() throws Exception {
        mockMvc.perform(post("/tickets/reserve")
                        .param("seatCategory", "ECONOMY")
                        .param("seatNumber", "B65")
                        .param("flightId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tickets"));
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testAddTicketValidValuesSecondTimeTicketIsAdded() throws Exception {
        mockMvc.perform(post("/tickets/reserve")
                        .param("seatCategory", "BUSINESS")
                        .param("seatNumber", "B14")
                        .param("flightId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tickets"));

        mockMvc.perform(post("/tickets/reserve")
                        .param("seatCategory", "PREMIUM")
                        .param("seatNumber", "B28")
                        .param("flightId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tickets"));
    }


    @Test
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN", "MODERATOR"}
    )
    void testReserveTicketWithInvalidValuesRedirectToTicketReserve() throws Exception {
        mockMvc.perform(post("/tickets/reserve")
                        .param("seatCategory", "")
                        .param("seatNumber", "")
                        .param("flightId", "1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tickets/reserve/1"));
    }

}