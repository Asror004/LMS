package com.company.controller;

import com.company.domain.basicsOfBasics.User;
import com.company.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService service;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(){
        return "adminPages/main";
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String pg, Model model){
        Page<User> students = service.getStudents(pg);

        model.addAttribute("students", students.getContent());
        model.addAttribute("pages", students.getTotalPages());
        model.addAttribute("hasNext", students.hasNext());
        model.addAttribute("current", students.getNumber());
        model.addAttribute("hasPrevious", students.hasPrevious());
        return "adminPages/studentList";
    }


//    @GetMapping("/groupSearch")
//    public String groupSearchPath(){
//
//    }
}
