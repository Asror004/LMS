package com.company.service;


import com.company.domain.basic.Lesson;
import com.company.dto.studentDTO.StudentsForAttendanceDTO;
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
    private final UserRepository userRepository;


    public TeacherService(EntityManager entityManager,
                          LessonRepository lessonRepository, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

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
        for (Integer id : ids) {

        }
    }

    public List<Integer> getStudentIdsInGroup(int groupId) {
        return userRepository.getUserIdsGroupId(groupId);
    }
}
