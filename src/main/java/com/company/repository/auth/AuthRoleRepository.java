package com.company.repository.auth;

import com.company.domain.auth.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {
}
