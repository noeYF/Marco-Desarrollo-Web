package com.proyecto.VisionGeekTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/user")
    public String userDashboard() {
        return "user_dashboard";
    }

}
