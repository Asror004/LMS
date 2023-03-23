package com.company.controller;

import com.company.domain.basic.News;
import com.company.repository.NewsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student/news")
public class NewsController {

    private final NewsRepository newsRepository;

    @GetMapping("/detailed")
    @PreAuthorize("hasRole('STUDENT')")
    public String news(Model model, @RequestParam("id") Integer id) {
        System.out.println("Detailed ga kegan id => " + id);
        News newsWithId = newsRepository.findFirstByDeletedFalseAndId(id);
        model.addAttribute("news", newsWithId);
        return "studentPages/detailed";
    }

}
