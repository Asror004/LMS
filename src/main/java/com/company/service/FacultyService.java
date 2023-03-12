package com.company.service;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Faculty;
import com.company.domain.basicsOfBasics.Language;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.mappers.auth.AuthMapper;
import com.company.mappers.faculty.FacultyMapper;
import com.company.repository.AuthUserRepository;
import com.company.repository.FacultyRepository;
import com.company.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyMapper facultyMapper;
    private final FacultyRepository facultyRepository;

    public void create(CreateFacultyDTO faculty) {
        Faculty faculty1 = facultyMapper.fromCreateDTO(faculty);
        facultyRepository.save(faculty1);
    }

    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }
}
