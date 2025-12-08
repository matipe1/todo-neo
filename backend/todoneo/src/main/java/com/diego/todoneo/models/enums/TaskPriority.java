package com.diego.todoneo.models.enums;

import java.util.List;

public enum TaskPriority {
    LOW, MEDIUM, HIGH, URGENT;

    public static final String REGEX = "LOW|MEDIUM|HIGH|URGENT";
    
    // Return a list with all the priorities
    public static List<TaskPriority> getAllPriorities() {
        return List.of(TaskPriority.values());
    }

}