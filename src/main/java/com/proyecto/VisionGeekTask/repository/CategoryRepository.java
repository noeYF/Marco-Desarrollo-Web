package com.proyecto.VisionGeekTask.repository;

import com.proyecto.VisionGeekTask.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
