package com.company.dto.studentDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentsForAttendanceDTO {
    @NotNull
    private Integer group_id;
    @NotNull
    private Integer lesson_id;
    @NotNull
    private String date;
    @NotNull
    @NotEmpty
    private Integer[] student_id;
}
