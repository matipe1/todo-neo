package com.diego.todoneo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.todoneo.dtos.TaskCreateDTO;
import com.diego.todoneo.dtos.TaskDTO;
import com.diego.todoneo.dtos.TaskUpdateDTO;
import com.diego.todoneo.services.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(Integer id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskCreateDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.ok().body(createdTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Integer id, @Valid @RequestBody TaskUpdateDTO updateDTO) {
        TaskDTO updatedTask = taskService.updateTask(id, updateDTO);
        return ResponseEntity.ok().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<TaskDTO> startTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.startTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<TaskDTO> finishTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.finishTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<TaskDTO> cancelTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.cancelTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<TaskDTO> archiveTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.archiveTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }

    @PatchMapping("/{id}/back")
    public ResponseEntity<TaskDTO> backTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.backTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }

    @PatchMapping("/{id}/reopen")
    public ResponseEntity<TaskDTO> reopenTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.reopenTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }

    @PatchMapping("/{id}/unarchive")
    public ResponseEntity<TaskDTO> unarchiveTask(@PathVariable Integer id) {
        TaskDTO updatedTask = taskService.unarchiveTask(id);
        return ResponseEntity.ok().body(updatedTask);
    }
}