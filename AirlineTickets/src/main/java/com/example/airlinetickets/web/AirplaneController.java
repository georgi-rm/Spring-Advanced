package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.CreateAirplaneDto;
import com.example.airlinetickets.services.AirplaneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping()
    public String airplanes() {

        return "airplanes";
    }

    @GetMapping("/details")
    public String airplanesDetails(@RequestParam("airplaneId") Long airplaneId) {

        return "airplane-details";
    }

    @ModelAttribute("createAirplaneDto")
    public CreateAirplaneDto initCreateAirplaneDto() {
        return new CreateAirplaneDto();
    }

    @GetMapping("/add")
    public String airplanesAdd() {

        return "airplane-add";
    }

    @PostMapping("/add")
    public String airplanesAdd(@Valid CreateAirplaneDto createAirplaneDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.airplaneService.create(createAirplaneDto)) {
            redirectAttributes.addFlashAttribute("createAirplaneDto", createAirplaneDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createAirplaneDto", bindingResult);

            return "redirect:/airplanes/add";
        }

        return "redirect:/";
    }
}
