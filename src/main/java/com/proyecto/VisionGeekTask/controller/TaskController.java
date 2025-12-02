package com.proyecto.VisionGeekTask.controller;

import com.proyecto.VisionGeekTask.model.Task;
import com.proyecto.VisionGeekTask.model.UserEntity;
import com.proyecto.VisionGeekTask.repository.TaskRepository;
import com.proyecto.VisionGeekTask.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String listTasks(@AuthenticationPrincipal CustomUserDetails userDetails,
                            Model model) {

        UserEntity user = userDetails.getUser();
        model.addAttribute("tasks", taskRepository.findByUser(user));
        return "tasks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "task_form";
    }

    @PostMapping("/save")
    public String saveTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                           @ModelAttribute Task task) {

        UserEntity user = userDetails.getUser();
        task.setUser(user);
        taskRepository.save(task);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
