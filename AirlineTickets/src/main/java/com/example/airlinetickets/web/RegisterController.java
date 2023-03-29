package com.example.airlinetickets.web;

import com.example.airlinetickets.models.dtos.binding.UserRegistrationDto;
import com.example.airlinetickets.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final UserService userService;

    private final SecurityContextRepository securityContextRepository;

    @Autowired
    public RegisterController(UserService userService, SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
    }

    @ModelAttribute("registrationDto")
    public UserRegistrationDto initRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto registrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        boolean isValid = true;

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            isValid = false;
            redirectAttributes.addFlashAttribute("passwordsDoesNotMatch", true);
        }

        if (userService.isEmailRegistered(registrationDto)) {
            isValid = false;
            redirectAttributes.addFlashAttribute("emailAlreadyRegistered", true);
        }

        if (userService.isUsernameRegistered(registrationDto)) {
            isValid = false;
            redirectAttributes.addFlashAttribute("usernameAlreadyRegistered", true);
        }

        if (bindingResult.hasErrors() || !isValid) {
            redirectAttributes.addFlashAttribute("registrationDto", registrationDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationDto", bindingResult);

            return "redirect:/users/register";
        }

        userService.registerUser(registrationDto, successfulAuth -> {
            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);

            strategy.setContext(context);

            securityContextRepository.saveContext(context, request, response);
        });
        return "redirect:/";
    }
}
