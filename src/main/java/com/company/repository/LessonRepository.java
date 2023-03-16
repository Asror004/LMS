package com.company.repository;

import com.company.domain.basic.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Lesson getLessonById(Integer id);
}