package com.company.dto.attendanceDTO;

import com.company.domain.basic.Lesson;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceAndClassesDTO {
    private Lesson lesson;
    private AttendanceByLessonIdDTO attendance;

}
