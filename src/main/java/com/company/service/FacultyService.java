package com.company.service;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.dto.facultyDTO.DeleteFacultyDTO;
import com.company.dto.facultyDTO.UpdateFacultyDTO;
import com.company.mappers.faculty.FacultyMapper;
import com.company.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void update(UpdateFacultyDTO faculty) {
        Faculty faculty1 = facultyMapper.updateFaculty(faculty);
        faculty1.setUpdatedAt(LocalDateTime.now());
        facultyRepository.update(faculty1.getId(),faculty1.getName(),faculty1.getUpdatedAt());
    }

    public void delete(DeleteFacultyDTO faculty) {
        Faculty faculty1 = facultyMapper.deleteFaculty(faculty);
        faculty1.setUpdatedAt(LocalDateTime.now());
        facultyRepository.delete(faculty1.getId(),faculty1.getUpdatedAt());
    }
}
