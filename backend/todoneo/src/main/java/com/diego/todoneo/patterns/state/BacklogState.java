package com.diego.todoneo.patterns.state;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.enums.TaskStatus;

@Component
public class BacklogState implements TaskState {
    
    @Override
    public void start(Task task) {
        throw new UnsupportedOperationException("Unable to 'start' a task from this state.");
    }

    @Override
    public void finish(Task task, Instant completionTime) {
        throw new UnsupportedOperationException("Unable to 'finish' a task from this state.");
    }

    @Override
    public void cancel(Task task) {
        task.setStatus(TaskStatus.CANCELED);
    }

    @Override
    public void archive(Task task) {
        throw new UnsupportedOperationException("Unable to 'archive' a task from this state.");
    }

    @Override
    public void checkDeadline(Task task) {
        throw new UnsupportedOperationException("Unable to 'checkDeadline' of a task from this state.");
    }

    @Override
    public void back(Task task) {
        throw new UnsupportedOperationException("Unable to 'back' from this state.");
    }

    @Override
    public void reopen(Task task) {
        throw new UnsupportedOperationException("Unable to 'reopen' a task from this state.");
    }

    @Override
    public void unarchive(Task task) {
        task.setStatus(TaskStatus.TODO);
    }
}
