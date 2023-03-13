package com.company.service;

import com.company.repository.auth.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final AuthUserRepository authRepository;
    public void get(){

    }
}
