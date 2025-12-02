package com.proyecto.VisionGeekTask.service;

import com.proyecto.VisionGeekTask.model.Task;
import com.proyecto.VisionGeekTask.model.UserEntity;
import com.proyecto.VisionGeekTask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findByUser(UserEntity user) {
        return repo.findByUser(user);
    }

    public Task findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Task task) {
        repo.save(task);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
