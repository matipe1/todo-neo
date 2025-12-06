package com.diego.todoneo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.todoneo.models.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
    public boolean existsByName(String name);
    public boolean existsByNameAndIdNot(String name, Integer id);
}
