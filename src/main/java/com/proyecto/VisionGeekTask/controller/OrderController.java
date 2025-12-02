package com.proyecto.VisionGeekTask.controller;

import com.proyecto.VisionGeekTask.model.Cart;
import com.proyecto.VisionGeekTask.model.Order;
import com.proyecto.VisionGeekTask.model.UserEntity;
import com.proyecto.VisionGeekTask.security.CustomUserDetails;
import com.proyecto.VisionGeekTask.service.CartService;
import com.proyecto.VisionGeekTask.service.OrderService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    public OrderController(OrderService orderService,
                           CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    // 👉 Evitar error al entrar a /orders
    @GetMapping
    public String redirectToProducts() {
        return "redirect:/products";
    }

    // 👉 Checkout (crear orden)
    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal CustomUserDetails userDetails) {

        UserEntity user = userDetails.getUser();
        Cart cart = cartService.getCart(user);

        // 🚨 Prevención: carrito vacío
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            return "redirect:/cart/view?empty=true";
        }

        // Crear order desde carrito
        Order order = orderService.createOrderFromCart(cart);

        // Vaciar carrito
        cartService.clearCart(user);

        return "redirect:/orders/" + order.getId();
    }

    // 👉 Ver detalle de la orden
    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {

        Order order = orderService.getOrder(id);

        if (order == null) {
            // 🚨 Manejo de error por orden no encontrada
            return "redirect:/products?orderNotFound=true";
        }

        model.addAttribute("order", order);
        return "order_detail";
    }
}
