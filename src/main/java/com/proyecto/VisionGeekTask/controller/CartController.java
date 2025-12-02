package com.proyecto.VisionGeekTask.controller;

import com.proyecto.VisionGeekTask.model.Cart;
import com.proyecto.VisionGeekTask.model.UserEntity;
import com.proyecto.VisionGeekTask.security.CustomUserDetails;
import com.proyecto.VisionGeekTask.service.CartService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 👉 Nueva ruta principal (evita error 404)
    @GetMapping
    public String cartRedirect() {
        return "redirect:/cart/view";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id,
                      @AuthenticationPrincipal CustomUserDetails userDetails) {

        UserEntity user = userDetails.getUser();
        cartService.addToCart(user, id);
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(@AuthenticationPrincipal CustomUserDetails userDetails,
                           Model model) {

        UserEntity user = userDetails.getUser();
        Cart cart = cartService.getCart(user);
        double total = cartService.calculateTotal(cart);

        model.addAttribute("cart", cart);
        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/remove/{itemId}")
    public String remove(@PathVariable Long itemId,
                         @AuthenticationPrincipal CustomUserDetails userDetails) {

        UserEntity user = userDetails.getUser();
        cartService.removeItem(user, itemId);
        return "redirect:/cart/view";
    }

    @PostMapping("/clear")
    public String clear(@AuthenticationPrincipal CustomUserDetails userDetails) {

        UserEntity user = userDetails.getUser();
        cartService.clearCart(user);
        return "redirect:/cart/view";
    }
}
