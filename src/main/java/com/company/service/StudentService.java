package com.company.service;

import com.company.dto.teacherDTO.UserLessonsDTO;
import com.company.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final SubjectRepository subjectRepository;

    public List<UserLessonsDTO> getStudentCourses(Integer id) {
        return null;
    }

    public List<String> getStudentSubjectNames(Integer id) {
        return subjectRepository.getAllSubjectNamesByGroupIdAndUserId(id);
    }
}
