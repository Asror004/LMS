package com.company.dto.studentDTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateStudentDTO(
        @NotBlank(message = "field.blank")
        String firstName,
        @NotBlank(message = "field.blank")
        String lastName,
        @NotBlank(message = "field.blank")
        String middleName,
        @Past(message = "date.invalid")
        @NotNull(message = "date.invalid")
        LocalDate birthDate,
        @Pattern(regexp = "^[A-Z]{2}\\d{7}?", message = "passport.not.correct")
        String passport,
        @NotBlank(message = "field.not.selected")
        String gender
) {
}
