package com.company.dto.userDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateUserDTO(
        @NotBlank(message = "field.blank")
        String firstName,
        @NotBlank(message = "field.blank")
        String lastName,
        @NotBlank(message = "field.blank")
        String middleName,
        @NotBlank(message = "field.blank")
        LocalDate birthDate,
        @NotBlank(message = "field.blank")
        @Pattern(regexp = "^[A-Z]{2}\\d{7}?", message = "passport.not.correct")
        String passport,
        @NotBlank(message = "field.not.selected")
        String gender
) {
}
