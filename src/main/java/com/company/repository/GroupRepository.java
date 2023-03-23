package com.company.repository;

import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("select g from Group g where g.deleted = false and g.name ilike '%' || ?1 || '%' order by g.name")
    Page<Group> findByName(String name, Pageable pageable);

    @Query("select g from Group g where g.deleted = false and g.faculty.name = ?1")
    Page<Group> findByDeletedFalseAndFaculty_Name(String name, Pageable pageable);

}
