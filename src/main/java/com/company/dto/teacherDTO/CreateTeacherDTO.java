package com.company.dto.teacherDTO;

import com.company.domain.basic.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CreateTeacherDTO(
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
