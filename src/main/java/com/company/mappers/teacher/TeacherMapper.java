package com.company.mappers.teacher;

import com.company.domain.basicsOfBasics.Teacher;
import com.company.dto.teacherDTO.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher fromCreateDTO(SetTeacherDTO dto);
    List<Teacher> fromCreateDTO(List<SetTeacherDTO> dtos);
    Teacher deleteTeacher(DeleteTeacherDTO dto);
    Teacher updateTeacher(UpdateTeacherDTO dto);
}

