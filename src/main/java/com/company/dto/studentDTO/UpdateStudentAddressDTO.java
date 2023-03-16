package com.company.dto.studentDTO;

import com.company.domain.basicsOfBasics.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateStudentAddressDTO(
        @NotNull(message = "Address must not be null")
        @NotBlank(message = "Address must not be blank")
        Address address
) {
}

