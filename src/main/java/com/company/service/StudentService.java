package com.company.service;

import com.company.dto.teacherDTO.UserLessonsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    public List<UserLessonsDTO> getStudentCourses(Integer id) {
        return null;
    }
}
