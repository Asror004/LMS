package com.company.repository;

import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, User> {


}
