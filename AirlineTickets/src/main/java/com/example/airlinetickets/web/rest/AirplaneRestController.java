package com.example.airlinetickets.web.rest;

import com.example.airlinetickets.models.dtos.AirplaneDetailsDto;
import com.example.airlinetickets.models.dtos.AirplaneViewDto;
import com.example.airlinetickets.models.entities.AirplaneEntity;
import com.example.airlinetickets.services.AirplaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AirplaneRestController {

    private final AirplaneService airplaneService;

    public AirplaneRestController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/airplanes/details/{airplaneId}")
    public ResponseEntity<AirplaneDetailsDto> getComments(@PathVariable("airplaneId") Long airplaneId) {

        Optional<AirplaneDetailsDto> airplane = airplaneService.findAirplaneById(airplaneId);

        return airplane.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/airplanes/all")
    public ResponseEntity<List<AirplaneViewDto>> getComments() {

        List<AirplaneViewDto> airplanes = airplaneService.getAllAirplanes();

        return ResponseEntity.ok(airplanes);
    }
}
