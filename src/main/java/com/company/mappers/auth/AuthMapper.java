package com.company.mappers.auth;

import com.company.domain.auth.AuthUser;
import com.company.dto.AuthUserRegisterDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthUser fromCreateDTO(AuthUserRegisterDTO dto);
    List<AuthUser> fromCreateDTO(List<AuthUser> dtos);
}

