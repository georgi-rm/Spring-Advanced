package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.binding.ReserveTicketDto;
import com.example.airlinetickets.models.dtos.view.FlightViewDto;
import com.example.airlinetickets.models.dtos.view.TicketViewDto;
import com.example.airlinetickets.services.FlightService;
import com.example.airlinetickets.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketService ticketService;

    private final FlightService flightService;

    @Autowired
    public TicketsController(TicketService ticketService, FlightService flightService) {
        this.ticketService = ticketService;
        this.flightService = flightService;
    }

    @GetMapping()
    public String reserveTicket(@AuthenticationPrincipal UserDetails userDetails,
                                Model model) {

        List<TicketViewDto> ticketViewDtoList = ticketService.getAllTicketsByUsername(userDetails.getUsername());

        model.addAttribute("ticketViewDtoList", ticketViewDtoList);

        return "tickets";
    }

    @GetMapping("/reserve/{flightId}")
    public String reserveTicket(@PathVariable("flightId") Long flightId,
                                Model model) {

        if (!model.containsAttribute("reserveTicketDto")) {
            model.addAttribute("reserveTicketDto", new ReserveTicketDto().setFlightId(flightId));
        }

        FlightViewDto flight = flightService.getFlightById(flightId);
        model.addAttribute("flightData", flight);

        return "ticket-reserve";
    }

    @PostMapping("/reserve")
    public String reserveTicket(@Valid ReserveTicketDto reserveTicketDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reserveTicketDto", reserveTicketDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.reserveTicketDto", bindingResult);

            return "redirect:/tickets/reserve/" + reserveTicketDto.getFlightId();
        }

        ticketService.reserve(reserveTicketDto, userDetails);

        return "redirect:/tickets";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTicket(@PathVariable("id") Long id,
                               @AuthenticationPrincipal UserDetails userDetails) {

        ticketService.deleteTicketById(id, userDetails);

        return "redirect:/tickets";
    }
}
