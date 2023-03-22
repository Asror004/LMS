package com.company.service;

import com.company.domain.basic.Faculty;
import com.company.repository.FacultyRepository;
import com.company.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;
    private final FacultyRepository facultyRepository;
    public Page<Faculty> getFaculties(Integer pg){
        Pageable pageable = PageRequest.of(pg, 2);
        return facultyRepository.findByDeletedFalse(pageable);
    }

    public boolean hasLesson(Integer id) {
        return repository.hasGroup(id);
    }
}
