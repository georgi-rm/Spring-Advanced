package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.binding.SearchFlightDto;
import com.example.airlinetickets.models.dtos.view.AirportViewDto;
import com.example.airlinetickets.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final AirportService airportService;

    @Autowired
    public HomeController(AirportService airportService) {
        this.airportService = airportService;
    }

    @ModelAttribute("searchFlightDto")
    public SearchFlightDto initSearchForm() {
        return new SearchFlightDto();
    }

    @GetMapping("/")
    public String index(Model model) {
        List<AirportViewDto> allAirports = this.airportService.getAllAirports();
        model.addAttribute("allAirports", allAirports);

        return "index";
    }

}
