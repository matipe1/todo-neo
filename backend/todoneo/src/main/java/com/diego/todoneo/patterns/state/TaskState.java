package com.diego.todoneo.patterns.state;

import java.time.Instant;

import com.diego.todoneo.models.Task;

public interface TaskState {
    public void start(Task task);
    public void finish(Task task, Instant completionTime);
    public void cancel(Task task);
    public void archive(Task task);
    public void checkDeadline(Task task);
    public void back(Task task);
    public void reopen(Task task);
    public void unarchive(Task task);
}
