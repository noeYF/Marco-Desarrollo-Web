package com.proyecto.VisionGeekTask.repository;

import com.proyecto.VisionGeekTask.model.Order;
import com.proyecto.VisionGeekTask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(UserEntity user);
}
