package com.company.dto.studentDTO;

import com.company.domain.basicsOfBasics.Document;
import jakarta.validation.constraints.NotNull;

public class UpdateStudentDTO {

    @NotNull(message = "Document must not be null")
    Document document;
}
