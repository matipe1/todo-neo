package com.diego.todoneo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabelCreateDTO {

    @NotBlank(message = "Label name cannot be blank")
    @Size(max = 50, message = "Label name cannot exceed 50 characters")
    private String name;
}
