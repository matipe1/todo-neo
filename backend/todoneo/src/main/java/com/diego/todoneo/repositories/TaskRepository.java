package com.diego.todoneo.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.enums.TaskStatus;


public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.workspace LEFT JOIN FETCH t.labels WHERE t.id = :id")
    Optional<Task> findByIdWithDetails(Integer id);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.workspace LEFT JOIN FETCH t.labels")
    List<Task> findAllWithDetails();

    List<Task> findByStatusAndDueDateBefore(List<TaskStatus> statuses, Instant now);
}
