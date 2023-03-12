package com.company.service;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.mappers.faculty.FacultyMapper;
import com.company.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
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
