package com.diego.todoneo.services;

import org.springframework.stereotype.Service;

import com.diego.todoneo.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
}
