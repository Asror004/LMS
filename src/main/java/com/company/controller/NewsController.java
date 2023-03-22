package com.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student/news")
public class NewsController {

    @GetMapping("/detailed")
    @PreAuthorize("hasRole('STUDENT')")
    public String news(Model model) {
        return "studentPages/detailed";
    }

}
