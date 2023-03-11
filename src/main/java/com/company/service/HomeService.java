package com.company.service;

import com.company.domain.auth.AuthUser;
import com.company.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final AuthUserRepository authRepository;
    public void get(){
        AuthUser authUser = authRepository.findById(2).orElseThrow();
        System.out.println(authUser);
    }
}
