package com.company.controller;

import com.company.domain.basic.Faculty;
import com.company.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LessonControllerRest {
    private final LessonService service;
    @GetMapping("/getFacultyList")
    @ResponseBody
    public List<Faculty> getFaculties() {
        return service.getFaculties();
    }
}
