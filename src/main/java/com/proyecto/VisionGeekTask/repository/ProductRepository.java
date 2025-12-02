package com.proyecto.VisionGeekTask.repository;

import com.proyecto.VisionGeekTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
