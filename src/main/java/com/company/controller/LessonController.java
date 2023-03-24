package com.company.controller;

import com.company.dto.lessonDTO.LessonCreateDTO;
import com.company.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService service;
    @GetMapping("/create")
    public String createPage(){
        return "adminPages/lesson/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute LessonCreateDTO dto, Model model){
        CompletableFuture.runAsync(() -> service.save(dto));
        model.addAttribute("save", true);
        return "adminPages/lesson/create";
    }
}
