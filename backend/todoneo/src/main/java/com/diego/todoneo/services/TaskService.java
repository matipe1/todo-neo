package com.diego.todoneo.services;

import org.springframework.stereotype.Service;

import com.diego.todoneo.patterns.state.TaskStateFactory;
import com.diego.todoneo.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskStateFactory taskStateFactory;
}
