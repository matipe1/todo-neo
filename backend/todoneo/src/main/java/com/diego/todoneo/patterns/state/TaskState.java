package com.diego.todoneo.patterns.state;

public interface TaskState {
    public void start();
    public void finish();
    public void cancel();
    public void archive();
    public void checkDeadline();
    public void back();
    public void reopen();
    public void unarchive();
}
