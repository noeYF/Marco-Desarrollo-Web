package com.proyecto.VisionGeekTask.service;

import com.proyecto.VisionGeekTask.model.Category;
import com.proyecto.VisionGeekTask.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category save(Category c) {
        return categoryRepo.save(c);
    }

    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }
}
