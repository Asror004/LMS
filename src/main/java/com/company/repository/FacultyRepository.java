package com.company.repository;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
