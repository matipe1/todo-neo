package com.diego.todoneo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.todoneo.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    
}
