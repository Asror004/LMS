package com.company.dto.facultyDTO;

import jakarta.validation.constraints.NotNull;

public record DeleteFacultyDTO(
        @NotNull
        Integer id
) {

}
