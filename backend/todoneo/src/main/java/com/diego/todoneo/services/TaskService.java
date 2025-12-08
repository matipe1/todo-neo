package com.diego.todoneo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.todoneo.dtos.TaskCreateDTO;
import com.diego.todoneo.dtos.TaskDTO;
import com.diego.todoneo.dtos.TaskUpdateDTO;
import com.diego.todoneo.models.Label;
import com.diego.todoneo.models.Task;
import com.diego.todoneo.models.Workspace;
import com.diego.todoneo.models.enums.TaskPriority;
import com.diego.todoneo.repositories.TaskRepository;
import com.diego.todoneo.utils.exceptions.ResourceNotFoundException;
import com.diego.todoneo.utils.mapper.TaskMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final WorkspaceService workspaceService;
    private final LabelService labelService;

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAllWithDetails();
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Task getTaskEntityById(Integer id) {
        return taskRepository.findByIdWithDetails(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public TaskDTO getTaskById(Integer id) {
        Task task = this.getTaskEntityById(id);
        return taskMapper.toDTO(task);
    }

    @Transactional
    public TaskDTO createTask(TaskCreateDTO taskDTO) {
        Workspace workspace = null;
        if (taskDTO.getWorkspaceId() != null) {
            workspace = workspaceService.getWorkspaceEntityById(taskDTO.getWorkspaceId());
        }
        Set<Label> labels = new HashSet<>();
        if (taskDTO.getLabelIds() != null) {
            labels = taskDTO.getLabelIds().stream()
                .map(labelId -> labelService.getLabelEntityById(labelId))
                .collect(Collectors.toSet());
        }

        Task task = taskMapper.toEntity(taskDTO, workspace, labels);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }

    @Transactional
    public TaskDTO updateTask(Integer id, TaskUpdateDTO updateDTO) {
        Task task = this.getTaskEntityById(id);

        if (updateDTO.getTitle() != null) {
            task.setTitle(updateDTO.getTitle());
        }
        if (updateDTO.getDescription() != null) {
            task.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getPriority() != null && updateDTO.getPriority().matches(TaskPriority.REGEX)) {
            task.setPriority(TaskPriority.valueOf(updateDTO.getPriority()));
        }
        if (updateDTO.getDueDate() != null) {
            task.setDueDate(updateDTO.getDueDate());
        }
        if (updateDTO.getReminderDate() != null) {
            task.setReminderDate(updateDTO.getReminderDate());
        }

        if (updateDTO.getWorkspaceId() != null) {
            Workspace workspace = workspaceService.getWorkspaceEntityById(updateDTO.getWorkspaceId());
            task.setWorkspace(workspace);
        }

        if (updateDTO.getLabelIds() != null) {
            Set<Label> labels = new HashSet<>();
            labels.stream()
                .filter(label -> updateDTO.getLabelIds().contains(label.getId()))
                .forEach(labels::add);
                
            task.setLabels(labels);
        }

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }
}
