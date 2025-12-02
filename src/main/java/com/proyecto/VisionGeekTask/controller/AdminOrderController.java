package com.proyecto.VisionGeekTask.controller;

import com.proyecto.VisionGeekTask.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderRepository orderRepo;

    public AdminOrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "admin/orders";
    }
}
