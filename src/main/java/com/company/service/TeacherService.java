package com.company.service;


import com.company.domain.basic.Attendance;
import com.company.domain.basic.Lesson;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.StudentsForAttendanceDTO;
import com.company.dto.teacherDTO.StudentsInLessonsDTO;
import com.company.dto.teacherDTO.UserDetailForAttendanceDTO;
import com.company.dto.teacherDTO.DailyLessonsDetail;
import com.company.repository.AttendanceRepository;
import com.company.repository.LessonRepository;
import com.company.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@ComponentScan("com.company")
@EnableJpaRepositories
public class TeacherService {
    private final EntityManager entityManager;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;


    public TeacherService(EntityManager entityManager,
                          LessonRepository lessonRepository, UserRepository userRepository,
                          AttendanceRepository attendanceRepository) {
        this.entityManager = entityManager;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<DailyLessonsDetail> getDailyLessonsDetailsByTeacherId(int id, String localDate) {
        String singleResult = entityManager.createQuery("select weekly_lessons(:id,:monday)", String.class)
                .setParameter("id", String.valueOf(id))
                .setParameter("monday", localDate).getSingleResult();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<DailyLessonsDetail> myObjects = objectMapper
                    .readValue(singleResult, objectMapper.getTypeFactory().constructCollectionType(List.class, DailyLessonsDetail.class));
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
        List<Integer> studentIds = List.of(studentsDto.getStudent_id());
        Lesson lesson = lessonRepository.getLessonById(studentsDto.getLesson_id());
        if (lesson == null) {
            // handle case when lesson is not found
            return false;
        }
        for (Integer id : ids) {
            if (studentIds.contains(id)) {
                Attendance attendance = Attendance.childBuilder()
                        .date(LocalDate.parse(studentsDto.getDate(), DateTimeFormatter.ISO_DATE))
                        .attended(true)
                        .lesson(lesson)
                        .user(new User(id))
                        .build();
                attendanceRepository.save(attendance);
            }else {
                Attendance attendance = Attendance.childBuilder()
                        .date(LocalDate.parse(studentsDto.getDate(), DateTimeFormatter.ISO_DATE))
                        .attended(false)
                        .lesson(lesson)
                        .user(new User(id))
                        .build();
                attendanceRepository.save(attendance);
            }
        }
        return true;
    }


    public List<Integer> getStudentIdsInGroup(int groupId) {
        return userRepository.getUserIdsGroupId(groupId);
    }
}
