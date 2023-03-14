package com.company.dto.facultyDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateFacultyDTO(
        @NotNull(message = "field.blank")
        Integer id,
        @Size(min=5,max = 30,message = "create.faculty.dto.size")
        String name
) {
}
