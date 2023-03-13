package com.company.repository;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Integer> {

}
