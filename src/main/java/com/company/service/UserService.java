package com.company.service;

import com.company.domain.auth.AuthRole;
import com.company.domain.auth.AuthUser;
import com.company.domain.basicsOfBasics.Address;
import com.company.repository.AddressRepository;
import com.company.domain.basicsOfBasics.Language;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.CreateStudentDTO;
import com.company.dto.studentDTO.UserUpdateDTO;
import com.company.mappers.auth.UserMapper;
import com.company.repository.LanguageRepository;
import com.company.repository.UserRepository;
import com.company.repository.auth.AuthRoleRepository;
import com.company.repository.auth.AuthUserRepository;
import com.company.security.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private final AddressRepository addressRepository;

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

        user.setAuthUserId(authUser.getId());
        user.setCreatedBy(session.getUser());

        repository.save(user);
    }

    public boolean hasPassport(String passport) {
        return repository.existsByPassport(passport);
    }

    public User findById(Integer id){
        return repository.findId(id);
    }

    public Optional<User> findById() {
        return repository.findById(session.getId());
    }

    public boolean updateAddress(Integer id, Address address) {
        Address savedAddress = addressRepository.save(address);
        return userRepository.updateAddress(id, savedAddress)==1;
    }

    public boolean updateUsername(Integer id, String username) {
        return userRepository.updateUsername(id, username)==1;
    }
}
