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
public class WorkspaceCreateDTO {
    
    @NotBlank(message = "Workspace name cannot be blank")
    @Size(max = 100, message = "Workspace name cannot exceed 100 characters")
    private String name;
}
