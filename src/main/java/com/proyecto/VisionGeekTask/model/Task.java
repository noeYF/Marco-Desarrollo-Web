package com.proyecto.VisionGeekTask.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 500)
    private String description;

    private LocalDate dueDate;

    private String priority;

    private String status;

    // Relación con User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
