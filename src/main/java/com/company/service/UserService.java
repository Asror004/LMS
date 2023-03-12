package com.company.service;

import com.company.domain.basicsOfBasics.User;
import com.company.dto.userDTO.CreateUserDTO;
import com.company.mappers.auth.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public void save(CreateUserDTO dto) {
        User user = mapper.fromCreateDTO(dto);
        System.out.println(user);
    }
}
