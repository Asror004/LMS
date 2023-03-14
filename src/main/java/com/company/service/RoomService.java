package com.company.service;

import com.company.domain.basic.Room;
import com.company.dto.roomDTO.*;
import com.company.mappers.auth.UserMapper;
import com.company.mappers.room.RoomMapper;
import com.company.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;

    public void create(CreateRoomDTO room) {
        Room room1 = roomMapper.fromCreateDTO(room);
        roomRepository.save(room1);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public void update(UpdateRoomDTO room) {
        Room room1 = roomMapper.updateRoom(room);
        room1.setUpdatedAt(LocalDateTime.now());
        roomRepository.update(room1.getId(),room1.getName(),room1.getUpdatedAt());
    }

    public void delete(DeleteRoomDTO room) {
        Room room1 = roomMapper.deleteRoom(room);
        room1.setUpdatedAt(LocalDateTime.now());
        roomRepository.delete(room1.getId(),room1.getUpdatedAt());
    }


}
