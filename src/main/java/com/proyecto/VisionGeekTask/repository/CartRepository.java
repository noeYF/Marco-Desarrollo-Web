package com.proyecto.VisionGeekTask.repository;

import com.proyecto.VisionGeekTask.model.Cart;
import com.proyecto.VisionGeekTask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(UserEntity user);
}
