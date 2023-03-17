package com.company.dto.attendanceDTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttendanceByLessonIdDTO {

    public AttendanceByLessonIdDTO(Integer lesson_id, Integer count) {
        this.lesson_id = lesson_id;
        this.count = count;
    }

    private Integer lesson_id;
    private Integer count;
}
