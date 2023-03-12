package com.company.dto.facultyDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFacultyDTO(
        @NotNull
        Integer id,

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name
) {
}
