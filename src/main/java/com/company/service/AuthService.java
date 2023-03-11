package com.company.service;

import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Language;
import com.company.dto.UserRegisterDTO;
import com.company.mappers.auth.AuthMapper;
import com.company.repository.AuthUserRepository;
import com.company.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthUserRepository authRepository;
    private final LanguageRepository languageRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public void save(UserRegisterDTO user) {
        Language language = languageRepository.findById(user.languageId()).orElseThrow();
        AuthUser authUser = AuthUser.childBuilder()
                .username(user.username())
                .language(language)
                .status(AuthUser.Status.INACTIVE)
                .password(passwordEncoder.encode(user.password())).build();

        System.out.println(authUser.toString());

        authRepository.save(authUser);
    }
}
