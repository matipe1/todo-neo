package com.diego.todoneo.utils.mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.diego.todoneo.dtos.TaskCreateDTO;
import com.diego.todoneo.dtos.TaskDTO;
import com.diego.todoneo.models.Label;
import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.Workspace;
import com.diego.todoneo.models.enums.TaskPriority;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final WorkspaceMapper workspaceMapper;
    private final LabelMapper labelMapper;
    
    public TaskDTO toDTO(Task task) {
        if (task == null) return null;
        return TaskDTO.builder()
            .id(task.getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus())
            .priority(task.getPriority())
            .dueDate(task.getDueDate())
            .reminderDate(task.getReminderDate())
            .createdAt(task.getCreatedAt())
            .updatedAt(task.getUpdatedAt())
            .completedAt(task.getCompletedAt())
            .workspace(workspaceMapper.toDTO(task.getWorkspace()))
            .labels(labelMapper.toDTOSet(task.getLabels()))
            .build();
    }

    public Task toEntity(TaskCreateDTO taskDTO, Workspace workspace, Set<Label> labels) {
        if (taskDTO == null) return null;
    
        Task task = Task.builder()
            .title(taskDTO.getTitle().trim())
            .description(taskDTO.getDescription())
            .dueDate(taskDTO.getDueDate())
            .reminderDate(taskDTO.getReminderDate())
            .workspace(workspace)
            .labels(labels)
            .build();
        
        if (taskDTO.getPriority() != null) {
            task.setPriority(TaskPriority.valueOf(taskDTO.getPriority()));
        }

        return task;
    }
}
