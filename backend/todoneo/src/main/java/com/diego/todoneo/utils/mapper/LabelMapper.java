package com.diego.todoneo.utils.mapper;

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
