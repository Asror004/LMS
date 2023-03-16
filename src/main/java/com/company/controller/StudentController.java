package com.company.controller;

import com.company.domain.basic.Subject;
import com.company.repository.SubjectRepository;
import com.company.security.UserSession;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final UserSession session;
    private final SubjectRepository subjectRepository;

    public StudentController(UserSession session, SubjectRepository subjectRepository) {
        this.session = session;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/main")
    @PreAuthorize("hasRole('STUDENT')")
    public String main() {
        return "studentPages/main";
    }

    @GetMapping("/news")
    @PreAuthorize("hasRole('STUDENT')")
    public String news() {
        return "studentPages/news";
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public String myCourses(Model model) {
//        Object attribute = model.getAttribute("usersession.getuserId");
//        System.out.println("attribute = " + attribute);

        Integer id = session.getId();
        System.out.println("id = " + id);

        List<Subject> myCourses = subjectRepository.findAllSubjectsOfStudentByUserId(id);//forEach(System.out::println);
        model.addAttribute("myCourses", myCourses);
        return "studentPages/my-courses";
    }

    @GetMapping("/schedule")
    @PreAuthorize("hasRole('STUDENT')")
    public String schedule() {
        return "studentPages/schedule";
    }

    @GetMapping("/retake")
    @PreAuthorize("hasRole('STUDENT')")
    public String retake() {
        return "studentPages/retake";
    }

    @GetMapping("/finals")
    @PreAuthorize("hasRole('STUDENT')")
    public String finals() {
        return "studentPages/finals";
    }

    @GetMapping("/study-plan")
    @PreAuthorize("hasRole('STUDENT')")
    public String studyPlan() {
        return "studentPages/study-plan";
    }

    @GetMapping("/info")
    @PreAuthorize("hasRole('STUDENT')")
    public String info() {
        return "studentPages/info";
    }

    @GetMapping("/survey")
    @PreAuthorize("hasRole('STUDENT')")
    public String survey() {
        return "studentPages/survey";
    }

    @GetMapping("/certificate")
    @PreAuthorize("hasRole('STUDENT')")
    public String certificate() {
        return "studentPages/certificate";
    }

    @GetMapping("/olympiad")
    @PreAuthorize("hasRole('STUDENT')")
    public String olympiad() {
        return "studentPages/olympiad";
    }

    @GetMapping("/diploma-work")
    @PreAuthorize("hasRole('STUDENT')")
    public String diplomaWork() {
        return "studentPages/diploma-work";
    }

    @GetMapping("/subject")
    @PreAuthorize("hasRole('STUDENT')")
    public String subject() {
        return "studentPages/subject";
    }

//    usersession.getuserId

}
