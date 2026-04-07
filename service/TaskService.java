package com.tathagata.taskmanager.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.tathagata.taskmanager.model.Task;
import com.tathagata.taskmanager.model.TaskStatus;
import com.tathagata.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // READ
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // UPDATE
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    // DELETE
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // FILTER
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
    public Page<Task> getTasksWithPagination(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size));
    }


    public List<Task> getTasksSorted(String field) {
        return taskRepository.findAll(Sort.by(field));
    }

    public List<Task> getTasksSortedDesc(String field) {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }
}