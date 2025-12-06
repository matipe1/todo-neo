package com.diego.todoneo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabelDTO {
    private Integer id;

    @NotBlank(message = "Label name cannot be blank")
    private String name;
}
