package com.company.repository;

import com.company.domain.basic.Faculty;
import com.company.domain.basic.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Transactional
    @Modifying
    @Query("update Subject f set f.name = ?2 , f.updatedAt = ?3 where f.id = ?1")
    void update(Integer id, String name, LocalDateTime updatedAt);
    @Transactional
    @Modifying
    @Query("update Subject f set f.deleted = true, f.updatedAt = ?2 where f.id = ?1")
    void delete(Integer id,LocalDateTime updatedAt);

    @Query("select f from Subject f where f.deleted = false")
    Page<Subject> findByDeletedFalse(Pageable pageable);

}
