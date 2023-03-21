package com.company.repository;

import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select (count(u) > 0) from User u where u.passport = ?1")
    boolean existsByPassport(String passport);

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
}
