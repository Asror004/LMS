package com.company.repository.auth;

import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsernameIgnoreCase(String username);
    @Query("""
            select a from AuthUser a inner join a.authRoles authRoles
            where a.deleted = false and a.username ilike '%'||?1||'%' and authRoles.code = 'STUDENT'""")
    Page<AuthUser> findByDeletedFalseAndUsernameLikeAndAuthRoles_Name(String username, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.language = ?1 where a.id = ?2")
    int updateLanguageById(Language language, Integer id);

    @Transactional
    @Query("select a from AuthUser a where a.username = ?1")
    AuthUser findByUsername(String name);
}
