package com.diego.todoneo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.todoneo.dtos.LabelDTO;
import com.diego.todoneo.services.LabelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @GetMapping
    public ResponseEntity<List<LabelDTO>> getAllLabels() {
        return ResponseEntity.ok(labelService.getAllLabels());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LabelDTO> getLabelById(@PathVariable Integer id) {
        return ResponseEntity.ok(labelService.getLabelById(id));
    }

    @PostMapping
    public ResponseEntity<LabelDTO> createLabel(@Valid @RequestBody LabelDTO labelDTO) {
        LabelDTO createdLabel = labelService.createLabel(labelDTO);
        return new ResponseEntity<>(createdLabel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabelDTO> updateLabel(@PathVariable Integer id, @Valid @RequestBody LabelDTO labelDTO) {
        return ResponseEntity.ok(labelService.updateLabel(id, labelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Integer id) {
        labelService.deleteLabel(id);
        return ResponseEntity.noContent().build();
    }
}
