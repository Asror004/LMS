package com.company.service;

<<<<<<< HEAD
public class TeacherService {
=======
import com.company.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final LanguageRepository languageRepository;
    private final PasswordEncoder passwordEncoder;

>>>>>>> developer

}
