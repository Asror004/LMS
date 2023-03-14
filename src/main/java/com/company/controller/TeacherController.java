package com.company.controller;

import com.company.dto.teacherDTO.WeeklyLessonsDetail;
import com.company.service.TeacherService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/teacher")
@ComponentScan("com.company")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/home")
    @PreAuthorize("hasRole('TEACHER')")
    public String homePage(){
        return "teacherPages/main";
    }

    @GetMapping("/attendance")
    @PreAuthorize("hasRole('TEACHER')")
    @ResponseBody
    public WeeklyLessonsDetail[] attendance(){
       return teacherService.getWeeklyLessonsDetailsByTeacherId("1","2023-03-13");
    }



//    @ExceptionHandler(Exception.class)
//    public String forbidden(Exception e){
//        System.out.println(Arrays.toString(e.getStackTrace()));
//        return "/errorPages/forbidden";
//    }
}