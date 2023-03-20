package com.company.service;


import com.company.domain.basic.Lesson;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.repository.TeacherRepository;
import com.company.dto.teacherDTO.StudentsInLessonsDTO;
import com.company.dto.teacherDTO.UserDetailForAttendanceDTO;
import com.company.dto.teacherDTO.WeeklyLessonsDetail;
import com.company.repository.LessonRepository;
import com.company.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.company")
@EnableJpaRepositories
public class TeacherService {
    private final EntityManager entityManager;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;


    public TeacherService(EntityManager entityManager,
                          UserRepository userRepository,
                          LessonRepository lessonRepository,
                          TeacherRepository teacherRepository) {
        this.entityManager = entityManager;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<WeeklyLessonsDetail> getWeeklyLessonsDetailsByTeacherId(int id, String localDate) {
        String singleResult = entityManager.createQuery("select weekly_lessons(:id,:monday)", String.class)
                .setParameter("id", id)
                .setParameter("monday", localDate).getSingleResult();
        System.out.println(singleResult);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<WeeklyLessonsDetail> myObjects = objectMapper
                    .readValue(singleResult, objectMapper.getTypeFactory().constructCollectionType(List.class, WeeklyLessonsDetail.class));
            myObjects.forEach(System.out::println);
            return myObjects;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public StudentsInLessonsDTO getUsersByLessonId(String  lessonId) {
        String singleResult = entityManager.createQuery("select get_users_in_lesson(:lesson_id)", String.class)
                .setParameter("lesson_id", lessonId).getSingleResult();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<UserDetailForAttendanceDTO> users = objectMapper
                    .readValue(singleResult, objectMapper.getTypeFactory().constructCollectionType(List.class, UserDetailForAttendanceDTO.class));
            Lesson lesson = lessonRepository.getLessonById(Integer.valueOf(lessonId));
            return StudentsInLessonsDTO.builder().lesson(lesson).users(users).build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }
}
