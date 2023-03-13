package com.company.dto.groupDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeleteGroupDTO(
        @NotNull
        @NotBlank
        Integer id
) {
}
