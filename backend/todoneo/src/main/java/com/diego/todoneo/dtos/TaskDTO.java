package com.diego.todoneo.dtos;

import java.time.Instant;
import java.util.Set;

import com.diego.todoneo.models.enums.TaskPriority;
import com.diego.todoneo.models.enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Instant dueDate;
    private Instant reminderDate;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant completedAt;

    private WorkspaceDTO workspace;
    private Set<LabelDTO> labels;
}
