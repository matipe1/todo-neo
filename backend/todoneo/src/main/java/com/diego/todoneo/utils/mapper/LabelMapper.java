package com.diego.todoneo.utils.mapper;

import org.springframework.stereotype.Component;

import com.diego.todoneo.dtos.LabelDTO;
import com.diego.todoneo.models.Label;

@Component
public class LabelMapper {
    
    public LabelDTO toDTO(Label label) {
        return LabelDTO.builder()
                .id(label.getId())
                .name(label.getName())
                .build();
    }

    public Label toEntity(LabelDTO labelDTO) {
        return Label.builder()
                .id(labelDTO.getId())
                .name(labelDTO.getName())
                .build();
    }
}
