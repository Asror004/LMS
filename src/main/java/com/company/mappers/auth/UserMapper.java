package com.company.mappers.auth;

import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.userDTO.CreateUserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromCreateDTO(CreateUserDTO dto);
    List<User> fromCreateDTO(List<CreateUserDTO> dtos);
}

