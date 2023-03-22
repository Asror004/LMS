package com.company.controller;

import com.company.domain.basicsOfBasics.Language;
import com.company.repository.LanguageRepository;
import com.company.repository.auth.AuthUserRepository;
import com.company.security.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class LanguageController {
    private final AuthUserRepository authUserRepository;
    private final LanguageRepository languageRepository;
    private final UserSession user;
    @GetMapping("/language/{languageCode}")
    public void changeLanguage(@PathVariable String languageCode) {
        Language language = languageRepository.findByCodeIgnoreCase(languageCode);
        authUserRepository.updateLanguageById(language, user.getId());
        user.getUser().setLanguage(language);
    }
}
