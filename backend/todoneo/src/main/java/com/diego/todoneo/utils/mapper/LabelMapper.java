package com.diego.todoneo.utils.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.diego.todoneo.dtos.LabelCreateDTO;
import com.diego.todoneo.dtos.LabelDTO;
import com.diego.todoneo.models.Label;

@Component
public class LabelMapper {
    
    public LabelDTO toDTO(Label label) {
        if (label == null) return null;
        return LabelDTO.builder()
                .id(label.getId())
                .name(label.getName())
                .build();
    }

    public Set<LabelDTO> toDTOSet(Set<Label> labels) {
        if (labels == null) return new HashSet<>();
        return labels.stream()
            .map(this::toDTO)
            .collect(Collectors.toSet());
    }

    public Label toEntity(LabelDTO labelDTO) {
        if (labelDTO == null) return null;
        return Label.builder()
                .id(labelDTO.getId())
                .name(labelDTO.getName())
                .build();
    }

    public Label toEntity(LabelCreateDTO labelCreateDTO) {
        if (labelCreateDTO == null) return null;
        return Label.builder()
                .name(labelCreateDTO.getName())
                .build();
    }
}
