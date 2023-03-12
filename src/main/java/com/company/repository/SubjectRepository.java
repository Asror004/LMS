package com.company.repository;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
