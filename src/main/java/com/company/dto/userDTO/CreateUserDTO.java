package com.company.dto.userDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record CreateUserDTO(
        @NotBlank(message = "field.blank")
        String firstName,
        @NotBlank(message = "field.blank")
        String lastName,
        @NotBlank(message = "field.blank")
        String middleName,
        @NotBlank(message = "field.blank")
        LocalDateTime birthDate,
        @NotBlank(message = "field.blank")
        @Pattern(regexp = "[A-Z]\\d{7}")
        String passport,
        @NotBlank(message = "field.not.selected")
        Boolean gender
) {
}
