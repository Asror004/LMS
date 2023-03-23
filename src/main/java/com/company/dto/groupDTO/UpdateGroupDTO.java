package com.company.dto.groupDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record UpdateGroupDTO(
        @NotNull
        @NotBlank
        Integer id,

        @NotNull(message = "Course is required")
        @NotBlank(message = "Course is required")
        @UniqueElements(message = "Group with this name already exists")
        Byte course,

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Faculty is required")
        @NotBlank(message = "Faculty is required")
        Integer faculty,

        @NotNull(message = "Teacher is required")
        @NotBlank(message = "Teacher is required")
        Integer teacher
) {
}
