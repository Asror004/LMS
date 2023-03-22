package com.company.controller;

import com.company.domain.basic.Faculty;
import com.company.responce.PageResponse;
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
}

