package com.company.repository;

import com.company.domain.basicsOfBasics.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<User,Integer> {


}
