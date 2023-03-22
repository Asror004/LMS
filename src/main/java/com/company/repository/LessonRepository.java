package com.company.repository;

import com.company.domain.basic.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Lesson getLessonById(Integer id);

    @Query("select l from Lesson l join User u on l.group.id=u.group.id where u.authUserId=?1")
    List<Lesson> findLessonsForStudentByUserIdUsingGroupId(Integer id);

    @Query("select exists (select l from Lesson l where l.teacher.user_id=?1)")
    boolean hasGroup(Integer id);
}