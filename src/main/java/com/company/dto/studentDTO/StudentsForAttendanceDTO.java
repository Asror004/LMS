package com.company.dto.studentDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class StudentsForAttendanceDTO {
    @NotNull
    private Integer group_id;
    @NotNull
    private Integer lesson_id;
    @NotNull
    private String date;
    @NotNull
    @NotEmpty
    private String[] student_id;
}
