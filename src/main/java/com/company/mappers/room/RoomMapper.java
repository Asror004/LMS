package com.company.mappers.room;

import com.company.domain.basic.Room;
import com.company.dto.roomDTO.CreateRoomDTO;
import com.company.dto.roomDTO.DeleteRoomDTO;
import com.company.dto.roomDTO.UpdateRoomDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room fromCreateDTO(CreateRoomDTO dto);
    List<Room> fromCreateDTO(List<CreateRoomDTO> dtos);
    Room deleteRoom(DeleteRoomDTO dto);
    Room updateRoom(UpdateRoomDTO dto);
}

