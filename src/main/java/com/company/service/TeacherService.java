package com.company.service;


import com.company.dto.teacherDTO.WeeklyLessonsDetail;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@ComponentScan("com.company")
public class TeacherService {
    private final EntityManager entityManager;

    public TeacherService(DataSource dataSource,
                          EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public WeeklyLessonsDetail[] getWeeklyLessonsDetailsByTeacherId(String id, String localDate){
        String singleResult = entityManager.createQuery("select weekly_lessons(:id,:monday)", String.class)
                .setParameter("id", id)
                .setParameter("monday", localDate).getSingleResult();
        System.out.println(singleResult);
        return new WeeklyLessonsDetail[]{};
    }

}
