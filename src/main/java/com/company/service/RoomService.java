package com.company.service;

import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Language;
import com.company.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final LanguageRepository languageRepository;
    private final PasswordEncoder passwordEncoder;


}
