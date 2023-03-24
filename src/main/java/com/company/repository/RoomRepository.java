package com.company.repository;

import com.company.domain.basic.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
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

    @Query("""
            select r from Room r
            where r.deleted  = false and
            r.id not in (select l.room.id from Lesson l
            where l.dayOfWeek = ?1 and l.para = ?2)
            """)
    Page<Room> getRooms(DayOfWeek day, int para, Pageable pageable);

    @Query("""
            select r from Room r
            where r.deleted  = false and r.name ilike '%' || ?3 || '%' and
            r.id not in (select l.room.id from Lesson l
            where l.dayOfWeek = ?1 and l.para = ?2)
            """)
    Page<Room> getRooms(DayOfWeek day, int para, String roomName, Pageable pageable);

}
