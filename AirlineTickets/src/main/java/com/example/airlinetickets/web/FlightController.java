package com.example.airlinetickets.web;

import com.example.airlinetickets.exceptions.FlightHasReservedTicketsException;
import com.example.airlinetickets.exceptions.FlightNotFoundException;
import com.example.airlinetickets.models.dtos.binding.CreateFlightDto;
import com.example.airlinetickets.models.dtos.view.AirplaneViewDto;
import com.example.airlinetickets.models.dtos.view.AirportViewDto;
import com.example.airlinetickets.models.dtos.view.FlightViewDto;
import com.example.airlinetickets.services.AirplaneService;
import com.example.airlinetickets.services.AirportService;
import com.example.airlinetickets.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    private final AirportService airportService;

    private final AirplaneService airplaneService;

    public FlightController(FlightService flightService, AirportService airportService, AirplaneService airplaneService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.airplaneService = airplaneService;
    }

    @GetMapping()
    public String flights(Model model) {

        List<FlightViewDto> allFlights = this.flightService.getAllFlights();
        model.addAttribute("title", "All flights");
        model.addAttribute("foundFlights", allFlights);

        return "flights";
    }

    @GetMapping("/add")
    public String flightsAdd(Model model) {

        if (!model.containsAttribute("createFlightDto")) {
            model.addAttribute("createFlightDto", new CreateFlightDto());
        }

        List<AirplaneViewDto> allAirplanes = this.airplaneService.getAllAirplanes();
        model.addAttribute("allAirplanes", allAirplanes);

        List<AirportViewDto> allAirports = this.airportService.getAllAirports();
        model.addAttribute("allAirports", allAirports);

        return "flight-add";
    }

    @PostMapping("/add")
    public String flightsAdd(@Valid CreateFlightDto createFlightDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        LocalDateTime localDateTimeNow = LocalDateTime.now();

        boolean isValid = true;

        if (createFlightDto.getOriginAirportId() != null && createFlightDto.getDestinationAirportId() != null) {
            if (Objects.equals(createFlightDto.getOriginAirportId(), createFlightDto.getDestinationAirportId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airportOriginAndDestinationAreTheSame", true);
            }
        }

        if (createFlightDto.getOriginAirportId() != null) {
            if (airportService.isAirportMissing(createFlightDto.getOriginAirportId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airportOriginDoesNotExist", true);
            }
        }

        if (createFlightDto.getDestinationAirportId() != null) {
            if (airportService.isAirportMissing(createFlightDto.getDestinationAirportId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airportDestinationDoesNotExist", true);
            }
        }

        if (createFlightDto.getAirplaneId() != null) {
            if (airplaneService.isAirplaneMissing(createFlightDto.getAirplaneId())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("airplaneDoesNotExist", true);
            }
        }

        if (createFlightDto.getDepartureDateTime() != null && createFlightDto.getArrivalDateTime() != null) {
            if (createFlightDto.getDepartureDateTime().isAfter(createFlightDto.getArrivalDateTime())) {
                isValid = false;
                redirectAttributes.addFlashAttribute("departureDateIsAfterArrivalDate", true);
            }
        }

        if (createFlightDto.getDepartureDateTime() != null) {
            if (createFlightDto.getDepartureDateTime().isBefore(localDateTimeNow)) {
                isValid = false;
                redirectAttributes.addFlashAttribute("departureDateIsBeforeCurrentDate", true);
            }
        }

        if (createFlightDto.getArrivalDateTime() != null) {
            if (createFlightDto.getArrivalDateTime().isBefore(localDateTimeNow)) {
                isValid = false;
                redirectAttributes.addFlashAttribute("arrivalDateIsBeforeCurrentDate", true);
            }
        }

        if (bindingResult.hasErrors() || !isValid) {
            redirectAttributes.addFlashAttribute("createFlightDto", createFlightDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createFlightDto", bindingResult);

            return "redirect:/flights/add";
        }

        flightService.create(createFlightDto);

        return "redirect:/flights";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(FlightNotFoundException.class)
    public ModelAndView onAirportNotFound(FlightNotFoundException flightNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("errors/flight-not-found");

        modelAndView.addObject("flightId", flightNotFoundException.getFlightId());

        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(FlightHasReservedTicketsException.class)
    public ModelAndView onAirportInUse(FlightHasReservedTicketsException flightHasReservedTicketsException) {
        ModelAndView modelAndView = new ModelAndView("errors/flight-has-reserved-tickets");

        modelAndView.addObject("flightId", flightHasReservedTicketsException.getFlightId());

        return modelAndView;
    }
}
