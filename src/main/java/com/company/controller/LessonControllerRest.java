package com.company.controller;

import com.company.domain.basic.Faculty;
import com.company.domain.basic.Group;
import com.company.domain.basic.Lesson;
import com.company.domain.basic.Room;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.dto.lessonDTO.RoomGetDTO;
import com.company.dto.lessonDTO.TeacherGetDTO;
import com.company.responce.PageResponse;
import com.company.responce.TeacherResponse;
import com.company.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LessonControllerRest {
    private final LessonService service;
    @GetMapping("/getFacultyList")
    @ResponseBody
    public PageResponse<Faculty> getFaculties(@RequestParam Integer pg) {
        Page<Faculty> faculties = service.getFaculties(pg);

        return new PageResponse<>(faculties.getContent(), faculties.hasPrevious(), faculties.hasNext(),
                (short) faculties.getNumber(), (short) faculties.getTotalPages());
    }

    @GetMapping("/getGroupList")
    public PageResponse<Group> getGroups(@RequestParam String fc, @RequestParam Integer pg){
        Page<Group> groups = service.getGroups(fc, pg);

        return new PageResponse<>(groups.getContent(), groups.hasPrevious(), groups.hasNext(),
                (short) groups.getNumber(), (short) groups.getTotalPages());
    }

    @GetMapping("/week")
    public List<Lesson> getWeek(@RequestParam String gr){
        return service.getWeek(gr);
    }

    @GetMapping("/getTeacherList")
    public PageResponse<TeacherResponse> getTeachers(@ModelAttribute TeacherGetDTO dto){
        return service.getTeachers(dto);
    }
    @GetMapping("/getRoomList")
    public PageResponse<Room> getRooms(@ModelAttribute RoomGetDTO dto){
        return service.getRooms(dto);
    }
}

