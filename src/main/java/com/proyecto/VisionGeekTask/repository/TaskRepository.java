package com.proyecto.VisionGeekTask.repository;

import com.proyecto.VisionGeekTask.model.Task;
import com.proyecto.VisionGeekTask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(UserEntity user);
}


