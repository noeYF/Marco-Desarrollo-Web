package com.proyecto.VisionGeekTask.repository;

import com.proyecto.VisionGeekTask.model.Cart;
import com.proyecto.VisionGeekTask.model.CartItem;
import com.proyecto.VisionGeekTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProduct(Cart cart, Product product);

}
