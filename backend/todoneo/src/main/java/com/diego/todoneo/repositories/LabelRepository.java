package com.diego.todoneo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.todoneo.models.Label;

public interface LabelRepository extends JpaRepository<Label, Integer> {
    
}
