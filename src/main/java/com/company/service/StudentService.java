package com.company.service;

import com.company.domain.basic.Lesson;
import com.company.dto.attendanceDTO.AttendanceAndClassesDTO;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.repository.AttendanceRepository;
import com.company.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;


    public List<AttendanceAndClassesDTO> getStudentCourses(Integer id) {

        List<Lesson> lessons = lessonRepository.findLessonsForStudentByUserIdUsingGroupId(id);
        List<String> attendancesStr = attendanceRepository.findAllAttendanceByLessonId(id);
        List<AttendanceByLessonIdDTO> attendances = new ArrayList<>();

        List<AttendanceAndClassesDTO> combinations = new ArrayList<>();

        for (String s : attendancesStr) {
            int index = s.indexOf(",");
            Integer lesson_id = Integer.valueOf(s.substring(0, index));
            Integer count = Integer.valueOf(s.substring(index + 1));
            attendances.add(new AttendanceByLessonIdDTO(lesson_id, count));
        }

        for (int i = 0; i < lessons.size(); i++) {
            combinations.add(new AttendanceAndClassesDTO(lessons.get(i), attendances.get(i)));
        }
        return combinations;
    }
}
