package com.company.mappers.attendance;

import com.company.domain.basic.Attendance;
import com.company.domain.basic.Group;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.dto.groupDTO.CreateGroupDTO;
import com.company.dto.groupDTO.DeleteGroupDTO;
import com.company.dto.groupDTO.UpdateGroupDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

        AttendanceByLessonIdDTO toAttendanceByLessonIdDTO(Attendance attendance);

        List<AttendanceByLessonIdDTO> toAttendanceByLessonIdDTOs(List<Attendance> attendances);

}
