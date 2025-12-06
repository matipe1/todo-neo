package com.diego.todoneo.utils.mapper;

import org.springframework.stereotype.Component;

import com.diego.todoneo.dtos.WorkspaceCreateDTO;
import com.diego.todoneo.dtos.WorkspaceDTO;
import com.diego.todoneo.models.Workspace;

@Component
public class WorkspaceMapper {
    
    public WorkspaceDTO toDTO(Workspace workspace) {
        if (workspace == null) return null;
        return WorkspaceDTO.builder()
                .id(workspace.getId())
                .name(workspace.getName())
                .build();
    }

    public Workspace toEntity(WorkspaceDTO workspaceDTO) {
        if (workspaceDTO == null) return null;
        return Workspace.builder()
                .id(workspaceDTO.getId())
                .name(workspaceDTO.getName())
                .build();
    }

    public Workspace toEntity(WorkspaceCreateDTO workspaceCreateDTO) { // Method Overloading
        if (workspaceCreateDTO == null) return null;
        return Workspace.builder()
                .name(workspaceCreateDTO.getName())
                .build();
    }
}
