package com.company.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/main")
    @PreAuthorize("hasRole('STUDENT')")
    public String main(){
        return "studentPages/main";
    }

    @GetMapping("/news")
    @PreAuthorize("hasRole('STUDENT')")
    public String news(){
        return "studentPages/news";
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public String myCourses(){
        return "studentPages/my-courses";
    }

    @GetMapping("/schedule")
    @PreAuthorize("hasRole('STUDENT')")
    public String schedule(){
        return "studentPages/schedule";
    }

    @GetMapping("/retake")
    @PreAuthorize("hasRole('STUDENT')")
    public String retake(){
        return "studentPages/retake";
    }

    @GetMapping("/finals")
    @PreAuthorize("hasRole('STUDENT')")
    public String finals(){
        return "studentPages/finals";
    }

    @GetMapping("/study-plan")
    @PreAuthorize("hasRole('STUDENT')")
    public String studyPlan(){
        return "studentPages/study-plan";
    }

    @GetMapping("/info")
    @PreAuthorize("hasRole('STUDENT')")
    public String info(){
        return "studentPages/info";
    }

    @GetMapping("/survey")
    @PreAuthorize("hasRole('STUDENT')")
    public String survey(){
        return "studentPages/survey";
    }

    @GetMapping("/certificate")
    @PreAuthorize("hasRole('STUDENT')")
    public String certificate(){
        return "studentPages/certificate";
    }

    @GetMapping("/olympiad")
    @PreAuthorize("hasRole('STUDENT')")
    public String olympiad(){
        return "studentPages/olympiad";
    }

    @GetMapping("/diploma-work")
    @PreAuthorize("hasRole('STUDENT')")
    public String diplomaWork(){
        return "studentPages/diploma-work";
    }

    @GetMapping("/subject")
    @PreAuthorize("hasRole('STUDENT')")
    public String subject(){
        return "studentPages/subject";
    }

}
