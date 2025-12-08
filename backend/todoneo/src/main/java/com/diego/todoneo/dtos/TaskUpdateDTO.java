package com.diego.todoneo.dtos;

import java.time.Instant;
import java.util.Set;

import com.diego.todoneo.models.enums.TaskPriority;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskUpdateDTO {

    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    // just strings in the following format: LOW, MEDIUM, HIGH, URGENT
    @Pattern(regexp = TaskPriority.REGEX, message = "Priority must be LOW, MEDIUM, HIGH, or URGENT")
    private String priority;

    @FutureOrPresent(message = "Due date cannot be in the past")
    private Instant dueDate;

    @FutureOrPresent(message = "Reminder date cannot be in the past")
    private Instant reminderDate;

    private Integer workspaceId;
    private Set<Integer> labelIds;
}
