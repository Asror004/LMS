package com.company.dto.facultyDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record CreateFacultyDTO (
        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name
) {
}
