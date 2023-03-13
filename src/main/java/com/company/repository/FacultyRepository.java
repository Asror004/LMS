package com.company.repository;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.UpdateFacultyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    @Transactional
    @Modifying
    @Query("update Faculty f set f.name = ?2 , f.updatedAt = ?3 where f.id = ?1")
    void update(Integer id, String name, LocalDateTime updatedAt);
    @Transactional
    @Modifying
    @Query("update Faculty f set f.deleted = true, f.updatedAt = ?2 where f.id = ?1")
    void delete(Integer id,LocalDateTime updatedAt);

}
