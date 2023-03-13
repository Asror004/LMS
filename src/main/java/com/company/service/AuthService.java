package com.company.service;

import com.company.repository.auth.AuthUserRepository;
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

    public void save() {
//        Language language = languageRepository.findById(1).orElseThrow();
//        AuthUser authUser = AuthUser.childBuilder()
//                .username(user.username())
//                .language(language)
//                .status(AuthUser.Status.INACTIVE)
//                .password(passwordEncoder.encode(user.password())).build();
//
//        authRepository.save(authUser);
    }
}
