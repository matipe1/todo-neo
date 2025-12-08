package com.diego.todoneo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.todoneo.dtos.WorkspaceCreateDTO;
import com.diego.todoneo.dtos.WorkspaceDTO;
import com.diego.todoneo.models.Workspace;
import com.diego.todoneo.repositories.WorkspaceRepository;
import com.diego.todoneo.utils.exceptions.DuplicateEntityException;
import com.diego.todoneo.utils.exceptions.ResourceNotFoundException;
import com.diego.todoneo.utils.mapper.WorkspaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    @Transactional(readOnly = true)
    public List<WorkspaceDTO> getAllWorkspaces() {
        return workspaceRepository.findAll().stream()
                .map(workspaceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Workspace getWorkspaceEntityById(Integer id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public WorkspaceDTO getWorkspaceById(Integer id) {
        Workspace workspace = this.getWorkspaceEntityById(id);
        return workspaceMapper.toDTO(workspace);
    }

    @Transactional
    public WorkspaceDTO createWorkspace(WorkspaceCreateDTO workspaceDTO) {
        this.validateNameUnique(workspaceDTO.getName(), null);

        Workspace workspace = workspaceMapper.toEntity(workspaceDTO);
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        return workspaceMapper.toDTO(savedWorkspace);
    }

    @Transactional
    public WorkspaceDTO updateWorkspace(Integer id, WorkspaceDTO workspaceDTO) {
        this.validateNameUnique(workspaceDTO.getName(), id); // Ignore the specific id name check

        Workspace existingWorkspace = this.getWorkspaceEntityById(id);

        existingWorkspace.setName(workspaceDTO.getName());
        Workspace updatedWorkspace = workspaceRepository.save(existingWorkspace);
        return workspaceMapper.toDTO(updatedWorkspace);
    }

    @Transactional
    public void deleteWorkspace(Integer id) {
        if (!workspaceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Workspace not found with id: " + id);
        }
        workspaceRepository.deleteById(id);
    }

    private void validateNameUnique(String name, Integer idToExclude) {
        boolean exists;
        
        if (idToExclude == null) {
            exists = workspaceRepository.existsByName(name);
        } else {
            exists = workspaceRepository.existsByNameAndIdNot(name, idToExclude);
        }
        if (exists) {
            throw new DuplicateEntityException("Workspace name '" + name + "' already exists");
        }
    }
}