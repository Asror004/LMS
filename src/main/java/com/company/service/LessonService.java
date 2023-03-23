package com.company.service;

import com.company.domain.basic.Faculty;
import com.company.domain.basic.Group;
import com.company.domain.basic.Lesson;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.repository.*;
import com.company.responce.PageResponse;
import com.company.responce.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public PageResponse<TeacherResponse> getTeachers(Integer pg, String username) {
        if (Objects.nonNull(username)) {
            return null;
        } else {
            Page<Teacher> teachers = teacherRepository.getTeachers(PageRequest.of(pg, 1));

            List<TeacherResponse> response = new ArrayList<>();

            teachers.getContent().forEach(teacher -> response.add(new TeacherResponse(userRepositor
                    .findByDeletedFalseAndAuthUserId(teacher.getUser_id()).getFirstName(),
                    teacher.getSubject().getName())));

            return new PageResponse<>(response, teachers.hasPrevious(), teachers.hasNext(),
                    (short) teachers.getNumber(), (short) teachers.getTotalPages());
        }
    }
}
