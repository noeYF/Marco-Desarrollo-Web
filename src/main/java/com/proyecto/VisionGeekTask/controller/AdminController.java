package com.proyecto.VisionGeekTask.controller;

import com.proyecto.VisionGeekTask.model.Task;
import com.proyecto.VisionGeekTask.model.UserEntity;
import com.proyecto.VisionGeekTask.repository.TaskRepository;
import com.proyecto.VisionGeekTask.repository.UserRepository;
import com.proyecto.VisionGeekTask.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;

    public AdminController(UserRepository userRepository,
                           TaskRepository taskRepository,
                           UserService userService) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    // DASHBOARD ADMIN
    @GetMapping
    public String dashboard() {
        return "admin_dashboard";
    }

    // GESTIÓN DE USUARIOS
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin_users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "admin_user_form";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute UserEntity user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    // GESTIÓN DE TAREAS
    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "admin_tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        model.addAttribute("task", task);
        return "admin_task_form";
    }

    @PostMapping("/tasks/update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/admin/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/admin/tasks";
    }
}
