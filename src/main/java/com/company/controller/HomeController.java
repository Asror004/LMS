package com.company.controller;

import com.company.domain.auth.AuthRole;
import com.company.repository.auth.AuthRoleRepository;
import com.company.security.UserSession;
import com.company.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService service;
    private final UserSession session;
    private final AuthRoleRepository authRoleRepository;
    @GetMapping("/home")
    public String homePage(){

        AuthRole admin = authRoleRepository.findByCode("ADMIN").orElseThrow();
        AuthRole teacher = authRoleRepository.findByCode("TEACHER").orElseThrow();

        if ( session.getUser().getAuthRoles().contains(admin) ) {
            return "redirect:/admin";
        } else if ( session.getUser().getAuthRoles().contains(teacher) ) {
            return "redirect:/teacher/home";
        }
        return "home";
    }
}
