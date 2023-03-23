package com.company.repository;

import com.company.domain.basic.Attendance;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    @Query("select a.lesson.id, count(a.attended) from Attendance a where a.user.authUserId = ?1 and a.attended = false group by a.lesson.id")
    List<String> findAllAttendanceByLessonId(Integer userId);
}


