package com.company.repository.auth;

import com.company.domain.auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsernameIgnoreCase(String username);
}
