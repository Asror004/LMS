package com.company.repository.auth;

import com.company.domain.auth.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {
    Optional<AuthRole> findByCode(String code);
}
