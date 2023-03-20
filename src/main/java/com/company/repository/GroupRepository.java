package com.company.repository;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import com.company.dto.groupDTO.CreateGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("select g from Group g where g.name ilike '%' || ?1 || '%'")
    Page<Group> findByName(String name, Pageable pageable);

//    @Query("select g.id from Group g where ")
//    Integer findGroupIdByUserID(Integer id);
}
