package com.proyecto.VisionGeekTask.service;

import com.proyecto.VisionGeekTask.model.*;
import com.proyecto.VisionGeekTask.repository.CartRepository;
import com.proyecto.VisionGeekTask.repository.CartItemRepository;
import com.proyecto.VisionGeekTask.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public Cart getCart(UserEntity user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public void addToCart(UserEntity user, Long productId) {
        Cart cart = getCart(user);
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem existing = cartItemRepository.findByCartAndProduct(cart, product);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
            cartItemRepository.save(existing);
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(1);
            cartItemRepository.save(item);
        }
    }

    public void removeItem(UserEntity user, Long itemId) {

        Cart cart = getCart(user);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow();

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("No autorizado");
        }

        cartItemRepository.delete(item);
    }

    public void clearCart(UserEntity user) {
        Cart cart = getCart(user);
        cart.getItems().clear();    // limpia lista
        cartRepository.save(cart);  // persiste cambios
    }

    public double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }
}
