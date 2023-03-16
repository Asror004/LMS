package com.company.repository;

import com.company.domain.basic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("select s.subjects from User s where s.authUserId = ?1")
    List<Subject> findAllSubjectsOfStudentByUserId(Integer userId);

    // Teacher lani Ismini qaytarish kere subject lar bo'yicha

}
