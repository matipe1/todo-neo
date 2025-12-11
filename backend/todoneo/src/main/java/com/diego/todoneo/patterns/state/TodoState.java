package com.diego.todoneo.patterns.state;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.enums.TaskStatus;

@Component
public class TodoState implements TaskState {

    @Override
    public void start(Task task) {
        task.setStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public void finish(Task task, Instant completionTime) {
        task.setStatus(TaskStatus.DONE);
        task.setCompletedAt(completionTime);
    }

    @Override
    public void cancel(Task task) {
        task.setStatus(TaskStatus.CANCELED);
    }

    @Override
    public void archive(Task task) {
        task.setStatus(TaskStatus.BACKLOG);
    }

    @Override
    public void checkDeadline(Task task) {
        task.setStatus(TaskStatus.OVERDUE);
    }

    @Override
    public void back(Task task) {
        throw new IllegalStateException("Unable to 'back' from this state.");
    }

    @Override
    public void reopen(Task task) {
        throw new IllegalStateException("Unable to 'reopen' a task from this state.");
    }

    @Override
    public void unarchive(Task task) {
        throw new IllegalStateException("Unable to 'unarchive' a task from this state.");
    }
    
}
