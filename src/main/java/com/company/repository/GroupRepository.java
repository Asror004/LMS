package com.company.repository;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GroupRepository extends JpaRepository<Group, Integer> {

}
