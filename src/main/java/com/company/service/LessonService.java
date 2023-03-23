package com.company.service;

import com.company.domain.basic.Faculty;
import com.company.domain.basic.Group;
import com.company.domain.basic.Lesson;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repository;
    private final FacultyRepository facultyRepository;
    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepositor;

    public Page<Faculty> getFaculties(Integer pg){
        return facultyRepository.findByDeletedFalse(PageRequest.of(pg, 2));
    }

    public boolean hasLesson(Integer id) {
        return repository.hasGroup(id);
    }

    public Page<Group> getGroups(String fc, Integer pg) {
        return groupRepository.findByDeletedFalseAndFaculty_Name(fc, PageRequest.of(pg, 2));
    }

    public List<Lesson> getWeek(String gr) {
        return repository.findLessons(gr);
    }

    public List<Teacher> getTeachers(String username) {
        if (Objects.nonNull(username)) {
            return null;
        } else {
            List<Teacher> teachers = teacherRepository.getTeachers();

//            teachers.stream().

            return teachers;
        }
    }
}
