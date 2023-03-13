package com.company.dto.facultyDTO;

import jakarta.validation.constraints.NotBlank;

public record CreateFacultyDTO (
        @NotBlank(message = "Name is required")
        String name
) {
}