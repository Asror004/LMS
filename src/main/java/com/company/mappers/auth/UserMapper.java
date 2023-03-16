package com.company.mappers.auth;

import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.CreateStudentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromCreateDTO(CreateStudentDTO dto);
    List<User> fromCreateDTO(List<CreateStudentDTO> dtos);
}

