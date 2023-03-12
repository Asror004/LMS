package com.company.mappers.auth;

import com.company.domain.auth.AuthUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    List<AuthUser> fromCreateDTO(List<AuthUser> dtos);
}

