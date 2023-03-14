package com.company.repository;

import com.company.domain.basicsOfBasics.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select (count(u) > 0) from User u where u.passport = ?1")
    boolean existsByPassport(String passport);

    Page<User> findByDeletedFalseAndGroup_Id(Integer id, Pageable pageable);
}
