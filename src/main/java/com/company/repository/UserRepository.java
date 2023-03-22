package com.company.repository;

import com.company.domain.basicsOfBasics.Address;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select (count(u) > 0) from User u where u.passport = ?1")
    boolean existsByPassport(String passport);

    @Override
    Optional<User> findById(Integer integer);

    Page<User> findByDeletedFalseAndGroup_Id(Integer id, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update User u set u.address=?2 where u.authUserId=?1")
    int updateAddress(Integer id, Address address);




    @Transactional
    @Modifying
    @Query("update AuthUser u set u.username=?2 where u.id=?1")
    int updateUsername(Integer id, String username);
}
