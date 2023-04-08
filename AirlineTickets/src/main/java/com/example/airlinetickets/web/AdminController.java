package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.UserRolesDto;
import com.example.airlinetickets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String seeAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsersDetails());
        return "user-all";
    }

    @PostMapping("/users")
    public String seeAllUsers(UserRolesDto userRolesDto) {

        userService.changeRoles(userRolesDto);

        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String seeAllUsers(@PathVariable("id") Long id,
                              Model model) {

        Optional<UserRolesDto> userRolesDtoOptional = userService.getUserRolesById(id);

        if (userRolesDtoOptional.isEmpty()) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", userRolesDtoOptional.get());
        return "user-roles";
    }
}
