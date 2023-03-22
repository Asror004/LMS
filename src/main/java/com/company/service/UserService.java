package com.company.service;

import com.company.domain.auth.AuthRole;
import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Language;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.CreateStudentDTO;
import com.company.dto.teacherDTO.DailyLessonsDetail;
import com.company.dto.teacherDTO.UserLessonsDTO;
import com.company.mappers.auth.UserMapper;
import com.company.repository.LanguageRepository;
import com.company.repository.UserRepository;
import com.company.repository.auth.AuthRoleRepository;
import com.company.repository.auth.AuthUserRepository;
import com.company.security.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    private final UserRepository userRepository;

    public void save(CreateStudentDTO dto) {
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

    public User findById(Integer id) {
        return repository.findId(id);
    }

    public List<UserLessonsDTO> getUserLessons(Integer id) throws JsonProcessingException {
        Integer groupId = findById(id).getGroup().getId();
        String result = userRepository.getUserLessonsDetail(groupId);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserLessonsDTO> myObjects = objectMapper
                .readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, UserLessonsDTO.class));
        return myObjects;
    }
}
