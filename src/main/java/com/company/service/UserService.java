package com.company.service;

import com.company.domain.auth.AuthRole;
import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Language;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.userDTO.CreateUserDTO;
import com.company.mappers.auth.UserMapper;
import com.company.repository.LanguageRepository;
import com.company.repository.UserRepository;
import com.company.repository.auth.AuthRoleRepository;
import com.company.repository.auth.AuthUserRepository;
import com.company.security.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final LanguageRepository languageRepository;
    private final AuthRoleRepository authRoleRepository;
    private final AuthUserRepository authUserRepository;
    private final UserSession session;

    public void save(CreateUserDTO dto) {
        User user = mapper.fromCreateDTO(dto);
        Language language = languageRepository.findById(1).orElseThrow();
        AuthRole authRole = authRoleRepository.findById(3).orElseThrow();

        AuthUser authUser = AuthUser.childBuilder()
                .username(user.getPassport())
                .password(encoder.encode(user.getFirstName().toLowerCase()))
                .language(language)
                .status(AuthUser.Status.ACTIVE)
                .authRoles(List.of(authRole)).build();

        authUserRepository.save(authUser);

//        AuthUser authUser = authUserRepository.findById(2).orElseThrow();

        user.setAuthUserId(authUser.getId());
        user.setCreatedBy(session.getUser());

        repository.save(user);
    }

    public boolean hasPassport(String passport) {
        return repository.existsByPassport(passport);
    }


}
