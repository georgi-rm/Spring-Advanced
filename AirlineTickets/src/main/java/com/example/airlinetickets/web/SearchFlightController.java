package com.example.airlinetickets.web;

import com.example.airlinetickets.exceptions.BadQueryParametersException;
import com.example.airlinetickets.models.dtos.binding.SearchFlightDto;
import com.example.airlinetickets.models.dtos.view.FlightViewDto;
import com.example.airlinetickets.services.AirportService;
import com.example.airlinetickets.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/flights/search")
public class SearchFlightController {

    private final FlightService flightService;

    private final AirportService airportService;

    public SearchFlightController(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }

    @GetMapping()
    public String flightsSearch(SearchFlightDto searchFlightDto,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            throw new BadQueryParametersException(bindingResult);
        }

        List<FlightViewDto> foundFlights = flightService.searchFlights(searchFlightDto);

        model.addAttribute("title", "Found flights");
        model.addAttribute("foundFlights", foundFlights);

        return "flights";
    }

    @PostMapping()
    public String flightsSearch(@Valid SearchFlightDto searchFlightDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        LocalDateTime localDateTimeNow = LocalDateTime.now();

        boolean isValid = true;

        if (searchFlightDto.getOriginAirportId() != null && searchFlightDto.getDestinationAirportId() != null) {
            if (Objects.equals(searchFlightDto.getOriginAirportId(), searchFlightDto.getDestinationAirportId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airportOriginAndDestinationAreTheSame", true);
            }
        }

        if (searchFlightDto.getOriginAirportId() != null) {
            if (airportService.isAirportMissing(searchFlightDto.getOriginAirportId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airportOriginDoesNotExist", true);
            }
        }

        if (searchFlightDto.getDestinationAirportId() != null) {
            if (airportService.isAirportMissing(searchFlightDto.getDestinationAirportId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airportDestinationDoesNotExist", true);
            }
        }

        if (searchFlightDto.getDepartureDate() != null) {
            if (searchFlightDto.getDepartureDate().isBefore(localDateTimeNow.toLocalDate())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("departureDateIsBeforeCurrentDate", true);
            }
        }

        if (bindingResult.hasErrors() || !isValid) {
            redirectAttributes.addFlashAttribute("searchFlightDto", searchFlightDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.searchFlightDto", bindingResult);

            return "redirect:/";
        }

        String searchQueryParameters = "?originAirportId=" + searchFlightDto.getOriginAirportId()
                + "&destinationAirportId=" + searchFlightDto.getDestinationAirportId()
                + "&departureDate=" + searchFlightDto.getDepartureDate().toString();


        return "redirect:/flights/search" + searchQueryParameters;
    }
}
