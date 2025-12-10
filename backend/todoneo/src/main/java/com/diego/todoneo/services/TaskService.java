package com.diego.todoneo.services;

import java.time.Instant;
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
import com.diego.todoneo.patterns.state.TaskStateFactory;
import com.diego.todoneo.repositories.TaskRepository;
import com.diego.todoneo.utils.exceptions.ResourceNotFoundException;
import com.diego.todoneo.utils.mapper.TaskMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskStateFactory taskStateFactory;
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
            for (Integer labelId : updateDTO.getLabelIds()) {
                Label label = labelService.getLabelEntityById(labelId);
                labels.add(label);
            }
                
            task.setLabels(labels);
        }

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public void deleteTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        taskRepository.delete(task);
    }

    @Transactional
    public TaskDTO startTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.start(taskStateFactory.getState(task.getStatus()));
        
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public TaskDTO finishTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.finish(taskStateFactory.getState(task.getStatus()), Instant.now());
        
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public TaskDTO cancelTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.cancel(taskStateFactory.getState(task.getStatus()));

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public TaskDTO archiveTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.archive(taskStateFactory.getState(task.getStatus()));

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }
    
    @Transactional
    public TaskDTO backTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.back(taskStateFactory.getState(task.getStatus()));

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public TaskDTO reopenTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.reopen(taskStateFactory.getState(task.getStatus()));

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public TaskDTO unarchiveTask(Integer id) {
        Task task = this.getTaskEntityById(id);
        task.unarchive(taskStateFactory.getState(task.getStatus()));

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Transactional
    public void markTasksAsOverdue(List<Task> tasks) {
        tasks.stream()
            .forEach(task -> {
                task.markAsOverdue(taskStateFactory.getState(task.getStatus()));
                    taskRepository.save(task);
            });
    }
}