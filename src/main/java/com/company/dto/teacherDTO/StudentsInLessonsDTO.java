package com.company.dto.teacherDTO;

import com.company.domain.basic.Lesson;
import com.company.domain.basicsOfBasics.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsInLessonsDTO {
    private Lesson lesson;
    private List<UserDetailForAttendanceDTO> users;
    private String  date;
}
