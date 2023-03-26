package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.CreateCityDto;
import com.example.airlinetickets.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ModelAttribute("createCityDto")
    public CreateCityDto initCreateCityDto() {
        return new CreateCityDto();
    }

    @GetMapping("/add")
    public String citiesAdd() {

        return "city-add";
    }

    @PostMapping("/add")
    public String citiesAdd(@Valid CreateCityDto createCityDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createCityDto", createCityDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createCityDto", bindingResult);

            return "redirect:/cities/add";
        }

        if (!this.cityService.create(createCityDto)) {

            redirectAttributes.addFlashAttribute("nameAlreadyExists", true);
            redirectAttributes.addFlashAttribute("createCityDto", createCityDto);

            return "redirect:/cities/add";
        }

        return "redirect:/";
    }
}
