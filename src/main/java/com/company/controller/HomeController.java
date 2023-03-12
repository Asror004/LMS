package com.company.controller;

import com.company.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService service;
    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
}
