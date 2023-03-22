package com.company.service;

import com.company.domain.basic.Faculty;
import com.company.repository.FacultyRepository;
import com.company.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;
    private final FacultyRepository facultyRepository;
    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }

    public boolean hasLesson(Integer id) {
        return repository.hasGroup(id);
    }
}
