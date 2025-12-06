package com.diego.todoneo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.todoneo.dtos.LabelCreateDTO;
import com.diego.todoneo.dtos.LabelDTO;
import com.diego.todoneo.models.Label;
import com.diego.todoneo.repositories.LabelRepository;
import com.diego.todoneo.utils.mapper.LabelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    @Transactional(readOnly = true)
    public List<LabelDTO> getAllLabels() {
        return labelRepository.findAll().stream()
                .map(labelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LabelDTO getLabelById(Integer id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Label not found with id: " + id));
        return labelMapper.toDTO(label);
    }

    @Transactional
    public LabelDTO createLabel(LabelCreateDTO labelDTO) {
        this.validateNameUnique(labelDTO.getName(), null);

        Label label = labelMapper.toEntity(labelDTO);
        Label savedLabel = labelRepository.save(label);
        return labelMapper.toDTO(savedLabel);
    }

    @Transactional
    public LabelDTO updateLabel(Integer id, LabelDTO labelDTO) {
        this.validateNameUnique(labelDTO.getName(), id); // Ignore the specific id name check

        Label existingLabel = labelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Label not found with id: " + id));

        existingLabel.setName(labelDTO.getName());
        Label updatedLabel = labelRepository.save(existingLabel);
        return labelMapper.toDTO(updatedLabel);
    }

    @Transactional
    public void deleteLabel(Integer id) {
        if (!labelRepository.existsById(id)) {
            throw new RuntimeException("Label not found with id: " + id);
        }
        labelRepository.deleteById(id);
    }

    private void validateNameUnique(String name, Integer idToExclude) {
        boolean exists;
        
        if (idToExclude == null) {
            exists = labelRepository.existsByName(name);
        } else {
            exists = labelRepository.existsByNameAndIdNot(name, idToExclude);
        }
        if (exists) {
            throw new RuntimeException("Label name '" + name + "' already exists");
        }
    }
}