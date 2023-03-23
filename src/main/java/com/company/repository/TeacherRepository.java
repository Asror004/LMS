package com.company.repository;

import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, User> {
    @Query("select t from Teacher t where t.user_id not in (select l.teacher.user_id from Lesson l)")
    Page<Teacher> getTeachers(Pageable pageable);
}