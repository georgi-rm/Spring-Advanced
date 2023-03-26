package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.SearchFlightDto;
import com.example.airlinetickets.models.entities.AirportEntity;
import com.example.airlinetickets.services.AirportService;
import com.example.airlinetickets.services.FlightService;
import com.example.airlinetickets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final AirportService airportService;
    private final FlightService flightService;
    private final UserService userService;

    @Autowired
    public HomeController(AirportService airportService, FlightService flightService, UserService userService) {
        this.airportService = airportService;
        this.flightService = flightService;
        this.userService = userService;
    }

    @ModelAttribute("searchFlightDto")
    public SearchFlightDto initBattleForm() {
        return new SearchFlightDto();
    }

    @GetMapping("/")
    public String loggedOutIndex(Model model) {
        List<AirportEntity> allAirports = this.airportService.getAllAirports();
        model.addAttribute("allAirports", allAirports);

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        List<AirportEntity> allAirportEntities = this.airportService.getAllAirports();
//        List<ShipDto> enemyShips = this.airplaneService.getShipsNotOwnedBy(loggedUserId);
//        List<ShipDto> sortedShips = this.airplaneService.getAllSorted();
//
        model.addAttribute("allAirports", allAirportEntities);
//        model.addAttribute("enemyShips", enemyShips);
//        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}
