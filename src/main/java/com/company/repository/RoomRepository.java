package com.company.repository;

import com.company.domain.basic.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Transactional
    @Modifying
    @Query("update Room f set f.deleted = true, f.updatedAt = ?2 where f.id = ?1")
    void delete(Integer id, LocalDateTime updatedAt);


    @Transactional
    @Modifying
    @Query("update Room f set f.deleted = false , f.updatedAt = ?2 where f.id = ?1")
    void update(Integer id, LocalDateTime updatedAt);

    @Transactional
    @Query("select r from Room r where r.name = ?1")
    Room findFirstByName(String name);


}
