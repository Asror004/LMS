package com.company.repository;

import com.company.domain.basic.Room;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
import com.company.responce.TeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("""
            select t from Teacher t
            where t.user_id not in (select l.teacher.user_id from Lesson l
            where l.dayOfWeek = ?1 and l.para = ?2)
            """)
    Page<Teacher> getTeachers(DayOfWeek day, int para, Pageable pageable);

    @Query("""
            select t from Teacher t join User u on u.authUserId = t.user_id
            where u.deleted  = false and u.firstName ilike '%' || ?3 || '%' and
            t.user_id not in (select l.teacher.user_id from Lesson l
            where l.dayOfWeek = ?1 and l.para = ?2)
            """)
    Page<Teacher> getTeachers(DayOfWeek day, int para, String firstName, Pageable pageable);


}