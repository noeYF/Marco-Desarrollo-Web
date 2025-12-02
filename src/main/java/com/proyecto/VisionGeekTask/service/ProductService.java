package com.proyecto.VisionGeekTask.service;

import com.proyecto.VisionGeekTask.model.Product;
import com.proyecto.VisionGeekTask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product save(Product p) {
        return productRepo.save(p);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}
