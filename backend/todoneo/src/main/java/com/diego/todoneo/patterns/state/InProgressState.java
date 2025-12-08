package com.diego.todoneo.patterns.state;

import org.springframework.stereotype.Component;

@Component
public class InProgressState implements TaskState {

    @Override
    public void start() {
        throw new UnsupportedOperationException("Unable to 'start' a task from this state.");
    }

    @Override
    public void finish() {
        throw new UnsupportedOperationException("Unable to 'finish' a task from this state.");
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Unable to 'cancel' a task from this state.");
    }

    @Override
    public void archive() {
        throw new UnsupportedOperationException("Unable to 'archive' a task from this state.");
    }

    @Override
    public void checkDeadline() {
        throw new UnsupportedOperationException("Unable to 'checkDeadline' of a task from this state.");
    }

    @Override
    public void back() {
        throw new UnsupportedOperationException("Unable to 'back' from this state.");
    }

    @Override
    public void reopen() {
        throw new UnsupportedOperationException("Unable to 'reopen' a task from this state.");
    }

    @Override
    public void unarchive() {
        throw new UnsupportedOperationException("Unable to 'unarchive' a task from this state.");
    }
    
}
