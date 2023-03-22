package com.company.repository.auth;

import com.company.domain.auth.AuthUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsernameIgnoreCase(String username);
    @Query("""
            select a from AuthUser a inner join a.authRoles authRoles
            where a.deleted = false and a.username ilike '%'||?1||'%' and authRoles.code = 'STUDENT'""")
    Page<AuthUser> findByDeletedFalseAndUsernameLikeAndAuthRoles_Name(String username, Pageable pageable);

    @Query("select a from AuthUser a where a.deleted = false and a.username ilike '%'||?1||'%'")
    Page<AuthUser> findByDeletedFalseAndUsernameLikeIgnoreCase(String username, Pageable pageable);

    @Query("update AuthUser a set a.username = ?2 where a.id = ?1")
    boolean updateUsername(Integer id, String username);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.language = ?1 where a.id = ?2")
    int updateLanguageById(Language language, Integer id);

}
