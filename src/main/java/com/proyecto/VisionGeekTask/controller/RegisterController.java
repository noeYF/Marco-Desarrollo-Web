package com.proyecto.VisionGeekTask.controller;

import com.proyecto.VisionGeekTask.model.UserEntity;
import com.proyecto.VisionGeekTask.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @PostMapping
    public String registerUser(
            @ModelAttribute("user") @Valid UserEntity user,
            BindingResult result,
            Model model
    ) {

        if (userService.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "error.user", "Ese usuario ya existe");
        }

        if (userService.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "error.user", "Ese email ya está registrado");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        // Rol por defecto
        user.setRole("USER");
        userService.create(user);

        return "redirect:/login?registered=true";
    }
}
