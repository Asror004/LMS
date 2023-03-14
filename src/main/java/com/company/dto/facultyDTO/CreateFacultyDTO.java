package com.company.dto.facultyDTO;

import jakarta.validation.constraints.Size;

public record CreateFacultyDTO (
        @Size(min=5,max = 30,message = "create.faculty.dto.size")
        String name
) {
}