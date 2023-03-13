package com.company.controller;

import com.company.domain.auth.AuthUser;
import com.company.security.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final UserSession userSession;

    @GetMapping("/home")
    @PreAuthorize("hasRole('TEACHER')")
    public String homePage(){
        return "teacherPages/main";
    }

    @GetMapping("/attendance")
    @PreAuthorize("hasRole('TEACHER')")
    public String attendance(){
        return "/teacherPages/attendance";
    }



    @ExceptionHandler(Exception.class)
    public String forbidden(){
        return "/errorPages/forbidden";
    }
}