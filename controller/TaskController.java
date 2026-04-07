package com.tathagata.taskmanager.controller;

import org.springframework.data.domain.Page;
import com.tathagata.taskmanager.model.Task;
import com.tathagata.taskmanager.model.TaskStatus;
import com.tathagata.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // CREATE
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    // GET ALL
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // FILTER
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/page")
    public List<Task> getTasksWithPagination(
            @RequestParam int page,
            @RequestParam int size) {
        return taskService.getTasksWithPagination(page, size).getContent();
    }

    @GetMapping("/sort")
    public List<Task> getTasksSorted(@RequestParam String field) {
        return taskService.getTasksSorted(field);
    }
    @GetMapping("/sort-desc")
    public List<Task> getTasksSortedDesc(@RequestParam String field) {
        return taskService.getTasksSortedDesc(field);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}