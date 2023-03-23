package com.company.repository;

import com.company.domain.basicsOfBasics.Address;
import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select (count(u) > 0) from User u where u.passport = ?1")
    boolean existsByPassport(String passport);

    @Override
    Optional<User> findById(Integer integer);

    Page<User> findByDeletedFalseAndGroup_Id(Integer id, Pageable pageable);

    //find GroupId by AuthUser
    @Query("select u.group.id from User u where u.authUserId = ?1")
    Integer findGroupIdByAuthUser(Integer id);


    @Query("select u.authUserId from User u where u.group.id=?1")
    List<Integer> getUserIdsGroupId(int group_id);



    @Transactional
    @Modifying
    @Query("update User u set u.group = ?1 where u.authUserId = ?2")
    void updateGroupByAuthUserId(Group group, int authUserId);

    @Transactional
    @Query("select u from User u where u.authUserId = ?1")
    User findId(Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.address=?2 where u.authUserId=?1")
    int updateAddress(Integer id, Address address);

    @Transactional
    @Modifying
    @Query("update AuthUser u set u.username=?2 where u.id=?1")
    int updateUsername(Integer id, String username);


}
