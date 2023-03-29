package com.example.airlinetickets.web;

import com.example.airlinetickets.exceptions.AirportInUseException;
import com.example.airlinetickets.exceptions.AirportNotFoundException;
import com.example.airlinetickets.models.dtos.CityDto;
import com.example.airlinetickets.models.dtos.binding.CreateAirportDto;
import com.example.airlinetickets.models.dtos.view.AirportViewDto;
import com.example.airlinetickets.services.AirportService;
import com.example.airlinetickets.services.CityService;
import com.example.airlinetickets.services.FlightService;
import com.example.airlinetickets.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;
    private final CityService cityService;

    private final FlightService flightService;


    public AirportController(AirportService airportService, CityService cityService,
                             FlightService flightService) {
        this.airportService = airportService;
        this.cityService = cityService;
        this.flightService = flightService;
    }

    @GetMapping()
    public String airports(Model model) {

        List<AirportViewDto> allAirports = airportService.getAllAirports();
        model.addAttribute("allAirports", allAirports);

        return "airports";
    }


    @GetMapping("/details/{airportId}")
    public String airportsDetails(@PathVariable("airportId") Long id, Model model) {

        AirportViewDto airport = airportService.getAirportById(id);

        model.addAttribute("airport", airport);

        return "airport-details";
    }

    @GetMapping("/add")
    public String airportsAdd(Model model) {

        if (!model.containsAttribute("createAirportDto")) {
            model.addAttribute("createAirportDto", new CreateAirportDto());
        }

        List<CityDto> allCities = this.cityService.getAllCities();
        model.addAttribute("allCities", allCities);

        return "airport-add";
    }

    @PostMapping("/add")
    public String airportsAdd(@Valid CreateAirportDto createAirportDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        boolean isValid = true;


        if (airportService.isNameUnavailable(createAirportDto.getName())) {
            isValid = false;
            redirectAttributes.addFlashAttribute("airportNameIsUnavailable", true);
        }

        if (airportService.isAbbreviationUnavailable(createAirportDto.getAbbreviation())) {
            isValid = false;
            redirectAttributes.addFlashAttribute("airportAbbreviationIsUnavailable", true);
        }

        if (bindingResult.hasErrors() || !isValid) {
            redirectAttributes.addFlashAttribute("createAirportDto", createAirportDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createAirportDto", bindingResult);

            return "redirect:/airports/add";
        }

        airportService.create(createAirportDto);

        return "redirect:/airports";
    }

    @PreAuthorize("@userService.isModerator(#userDetails)")
    @DeleteMapping("/{id}")
    public String deleteAirport(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long id) {

        if (flightService.hasFlightsFromToAirportWithId(id)) {
            throw new AirportInUseException(id);
        }

        airportService.deleteAirportById(id);

        return "redirect:/airports";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(AirportNotFoundException.class)
    public ModelAndView onAirportNotFound(AirportNotFoundException airportNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("errors/airport-not-found");

        modelAndView.addObject("airportId", airportNotFoundException.getAirportId());

        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(AirportInUseException.class)
    public ModelAndView onAirportInUse(AirportInUseException airportNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("errors/airport-in-use");

        modelAndView.addObject("airportId", airportNotFoundException.getAirportId());

        return modelAndView;
    }
}
