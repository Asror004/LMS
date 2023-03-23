package com.company.controller;

import com.company.domain.basic.Lesson;
import com.company.domain.basic.News;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.attendanceDTO.AttendanceAndClassesDTO;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.repository.*;
import com.company.security.UserSession;
import com.company.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final NewsService newsService;
    private final UserSession session;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final AttendanceRepository attendanceRepository;
    private final NewsRepository newsRepository;

    public StudentController(NewsService newsService, UserSession session, SubjectRepository subjectRepository,
                             LessonRepository lessonRepository,
                             GroupRepository groupRepository,
                             AttendanceRepository attendanceRepository,
                             NewsRepository newsRepository) {
        this.newsService = newsService;
        this.session = session;
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.attendanceRepository = attendanceRepository;
        this.newsRepository = newsRepository;
    }

//    public Page<News> getNews(String pg) {
//        Pageable pageable = Pageable.ofSize(8);
//
//        if ( Objects.nonNull(pg) ) {
//            pageable = PageRequest.of(Integer.parseInt(pg), 8);
//        }
//
//        return newsRepository.findAllByDeletedFalseInDescendingOrder2(pageable);
//    }

    @GetMapping("/main")
    @PreAuthorize("hasRole('STUDENT')")
    public String main() {
        return "studentPages/main";
    }

    @GetMapping("/news")
    @PreAuthorize("hasRole('STUDENT')")
    public String news(Model model) {

        List<News> news = newsRepository.findAllByDeletedFalseInDescendingOrder();
        model.addAttribute("news", news);
        Object id = model.getAttribute("id");
        return "studentPages/news";
    }

    @RequestMapping(value = "/newspaging/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public Page<News> newsPaging(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        return newsService.getNewsPagination(pageNumber, pageSize, null);

    }


    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public String myCourses(Model model) {
        Integer id = session.getId(); // student id

        List<Lesson> lessons = lessonRepository.findLessonsForStudentByUserIdUsingGroupId(id);// agar ishlamasa group ni id sini bervoriladi
        List<String> attendancesStr = attendanceRepository.findAllAttendanceByLessonId(id);
        List<AttendanceByLessonIdDTO> attendances = new ArrayList<>();

        List<AttendanceAndClassesDTO> combinations = new ArrayList<>();

        for (String s : attendancesStr) {
            int index = s.indexOf(",");
            Integer lesson_id = Integer.valueOf(s.substring(0, index));
            Integer count = Integer.valueOf(s.substring(index + 1));
            attendances.add(new AttendanceByLessonIdDTO(lesson_id, count));
        }

        for (int i = 0; i < lessons.size(); i++) {
            combinations.add(new AttendanceAndClassesDTO(lessons.get(i), attendances.get(i)));
        }

        System.out.println("****************");
        attendances.forEach(System.out::println);
        System.out.println("****************");


        model.addAttribute("attendances", attendances);
        model.addAttribute("lessons", lessons);
        model.addAttribute("combinations", combinations);
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

}
