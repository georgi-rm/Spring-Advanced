package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.CityDto;
import com.example.airlinetickets.models.dtos.binding.CreateAirportDto;
import com.example.airlinetickets.models.dtos.view.AirportViewDto;
import com.example.airlinetickets.services.AirportService;
import com.example.airlinetickets.services.CityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;
    private final CityService cityService;

    public AirportController(AirportService airportService, CityService cityService) {
        this.airportService = airportService;
        this.cityService = cityService;
    }

    @GetMapping()
    public String airports(Model model) {

        List<AirportViewDto> allAirports = airportService.getAllAirports();
        model.addAttribute("allAirports", allAirports);

        return "airports";
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
}
