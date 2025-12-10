package com.diego.todoneo.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.enums.TaskStatus;
import com.diego.todoneo.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskDeadlineScheduler {
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(TaskDeadlineScheduler.class);

    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void checkDeadlines() {

        log.info("Running Task Deadline Scheduler at {}", Instant.now());

        List<TaskStatus> PossibleDueStatuses = Arrays.asList(TaskStatus.TODO, TaskStatus.IN_PROGRESS);
        List<Task> tasks = taskRepository.findByStatusAndDueDateBefore(PossibleDueStatuses, Instant.now());

        if (tasks.isEmpty()) {
            log.info("No tasks with deadlines to check.");
            return;
        } else {
            try {
                taskService.markTasksAsOverdue(tasks);
            } catch (Exception e) {
                log.error("Error while checking deadline for tasks: {}", e.getMessage());
            }
        }
    }
}
