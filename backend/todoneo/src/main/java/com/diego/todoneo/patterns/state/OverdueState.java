package com.diego.todoneo.patterns.state;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.enums.TaskStatus;

@Component
public class OverdueState implements TaskState {

    @Override
    public void start(Task task) {
        throw new IllegalStateException("Unable to 'start' a task from this state.");
    }

    @Override
    public void finish(Task task, Instant completionTime) {
        task.setStatus(TaskStatus.DONE);
        task.setCompletedAt(completionTime);
    }

    @Override
    public void cancel(Task task) {
        throw new IllegalStateException("Unable to 'cancel' a task from this state.");
    }

    @Override
    public void archive(Task task) {
        throw new IllegalStateException("Unable to 'archive' a task from this state.");
    }

    @Override
    public void checkDeadline(Task task) {
        throw new IllegalStateException("Unable to 'checkDeadline' of a task from this state.");
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
