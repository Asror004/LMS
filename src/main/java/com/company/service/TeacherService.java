package com.company.service;


import com.company.domain.basic.Attendance;
import com.company.domain.basic.Lesson;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.StudentsForAttendanceDTO;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.dto.teacherDTO.StudentsInLessonsDTO;
import com.company.dto.teacherDTO.UserDetailForAttendanceDTO;
import com.company.dto.teacherDTO.WeeklyLessonsDetail;
import com.company.repository.AttendanceRepository;
import com.company.repository.LessonRepository;
import com.company.repository.TeacherRepository;
import com.company.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final EntityManager entityManager;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;




    public List<WeeklyLessonsDetail> getWeeklyLessonsDetailsByTeacherId(int id, String localDate) {
        String singleResult = entityManager.createQuery("select weekly_lessons(:id,:monday)", String.class)
                .setParameter("id", String.valueOf(id))
                .setParameter("monday", localDate).getSingleResult();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<WeeklyLessonsDetail> myObjects = objectMapper
                    .readValue(singleResult, objectMapper.getTypeFactory().constructCollectionType(List.class, WeeklyLessonsDetail.class));
            return myObjects;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public StudentsInLessonsDTO getUsersByLessonId(String lessonId) throws JsonProcessingException, IllegalArgumentException {
        String singleResult = entityManager.createQuery("select get_users_in_lesson(:lesson_id)", String.class)
                .setParameter("lesson_id", lessonId).getSingleResult();
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDetailForAttendanceDTO> users = objectMapper
                .readValue(singleResult, objectMapper.getTypeFactory().constructCollectionType(List.class, UserDetailForAttendanceDTO.class));
        Lesson lesson = lessonRepository.getLessonById(Integer.valueOf(lessonId));
        return StudentsInLessonsDTO.builder().lesson(lesson).users(users).build();
    }
    public boolean completeLesson(StudentsForAttendanceDTO studentsDto){
        List<Integer> ids = getStudentIdsInGroup(studentsDto.getGroup_id());
        String[] studentIds = studentsDto.getStudent_id();
        for (String studentId : studentIds) {
            if (ids.contains(Integer.parseInt(studentId))) {
                Attendance attendance = Attendance.childBuilder()
                        .date(LocalDate.parse(studentsDto.getDate(), DateTimeFormatter.ISO_DATE))
                        .attended(true)
                        .lesson(new Lesson(studentsDto.getLesson_id()))
                        .user(new User(Integer.parseInt(studentId)))
                        .build();
                attendanceRepository.save(attendance);
            }
        }
        return true;
    }

    public List<Teacher> findAll(){
       return teacherRepository.findAll();
    }

    public List<Integer> getStudentIdsInGroup(int groupId) {
        return userRepository.getUserIdsGroupId(groupId);
    }
}
