package com.proyecto.VisionGeekTask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    @Column(name = "image_url")
    private String imageUrl;

    // Si tienes category:
    // @ManyToOne
    // @JoinColumn(name = "category_id")
    // private Category category;
}
