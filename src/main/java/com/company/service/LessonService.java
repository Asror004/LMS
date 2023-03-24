package com.company.service;

import com.company.domain.basic.*;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.lessonDTO.LessonCreateDTO;
import com.company.dto.lessonDTO.RoomGetDTO;
import com.company.dto.lessonDTO.TeacherGetDTO;
import com.company.mappers.lesson.LessonMapper;
import com.company.repository.*;
import com.company.responce.PageResponse;
import com.company.responce.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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
    private final RoomRepository roomRepository;
    private final LessonMapper lessonMapper;
    private final SubjectRepository subjectRepository;

    public Page<Faculty> getFaculties(Integer pg) {
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

    public PageResponse<TeacherResponse> getTeachers(TeacherGetDTO dto) {
        Page<Teacher> teachers;

        if ( Objects.nonNull(dto.firstName()) ) {
            System.out.println(dto.firstName());
            teachers = teacherRepository.getTeachers(DayOfWeek.of(dto.day() + 1), dto.para(), dto.firstName(),
                    PageRequest.of(dto.pg(), 1));
        }
        else
            teachers = teacherRepository.getTeachers(DayOfWeek.of(dto.day() + 1), dto.para(),
                    PageRequest.of(dto.pg(), 1));

        System.out.println(teachers.getContent());

        List<TeacherResponse> response = new ArrayList<>();

        teachers.getContent().forEach(teacher -> response.add(new TeacherResponse(userRepositor
                .findByDeletedFalseAndAuthUserId(teacher.getUser_id()).getFirstName(),
                teacher.getSubject().getName())));

        return new PageResponse<>(response, teachers.hasPrevious(), teachers.hasNext(),
                (short) teachers.getNumber(), (short) teachers.getTotalPages());
    }

    public PageResponse<Room> getRooms(RoomGetDTO dto){
        Page<Room> rooms;

        if ( Objects.nonNull(dto.name()) ) {
            System.out.println(dto.name());
            rooms = roomRepository.getRooms(DayOfWeek.of(dto.day() + 1), dto.para(), dto.name(),
                    PageRequest.of(dto.pg(), 3));
        }
        else
            rooms = roomRepository.getRooms(DayOfWeek.of(dto.day() + 1), dto.para(),
                    PageRequest.of(dto.pg(), 3));

        return new PageResponse<>(rooms.getContent(), rooms.hasPrevious(), rooms.hasNext(),
                (short) rooms.getNumber(), (short) rooms.getTotalPages());
    }

    public void save(LessonCreateDTO dto) {
        User user = userRepositor.findByDeletedFalseAndFirstName(dto.teacher());
        Teacher teacher = teacherRepository.findById(user.getAuthUserId()).orElseThrow();
        Subject subject = subjectRepository.findByDeletedFalseAndName(teacher.getSubject().getName());
        Group group = groupRepository.findByName(dto.group());
        Room room = roomRepository.findFirstByName(dto.room());

        Lesson lesson = Lesson.childBuilder()
                .subject(subject)
                .teacher(teacher)
                .group(group)
                .dayOfWeek(DayOfWeek.of(dto.day() + 1))
                .para(dto.para())
                .room(room)
                .build();

        lessonRepository.save(lesson);
    }
}
