package com.company.controller;

import com.company.domain.basic.Lesson;
import com.company.domain.basic.News;
import com.company.domain.basicsOfBasics.Address;
import com.company.dto.attendanceDTO.AttendanceAndClassesDTO;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.dto.teacherDTO.UserLessonsDTO;
import com.company.repository.*;
import com.company.security.UserSession;
import com.company.service.NewsService;
import com.company.service.StudentService;
import com.company.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final NewsService newsService;
    private final UserSession session;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;
    private final NewsRepository newsRepository;
    private StudentService studentService;


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


//    @GetMapping("/my-courses")
//    @PreAuthorize("hasRole('STUDENT')")
//    public String myCourses(Model model) {
//        Integer id = session.getId(); // student id
//
//        List<Lesson> lessons = lessonRepository.findLessonsForStudentByUserIdUsingGroupId(id);// agar ishlamasa group ni id sini bervoriladi
//        List<String> attendancesStr = attendanceRepository.findAllAttendanceByLessonId(id);
//        List<AttendanceByLessonIdDTO> attendances = new ArrayList<>();
//
//        List<AttendanceAndClassesDTO> combinations = new ArrayList<>();
//
//        for (String s : attendancesStr) {
//            int index = s.indexOf(",");
//            Integer lesson_id = Integer.valueOf(s.substring(0, index));
//            Integer count = Integer.valueOf(s.substring(index + 1));
//            attendances.add(new AttendanceByLessonIdDTO(lesson_id, count));
//        }
//
//        for (int i = 0; i < lessons.size(); i++) {
//            combinations.add(new AttendanceAndClassesDTO(lessons.get(i), attendances.get(i)));
//        }
//
////        model.addAttribute("attendances", attendances);
////        model.addAttribute("lessons", lessons);
//        model.addAttribute("combinations", combinations);
//        return "studentPages/my-courses";
//    }
@GetMapping("/my-courses")
@PreAuthorize("hasRole('STUDENT')")
public String myCourses(Model model) {
    List<String> subjectsName = studentService.getStudentSubjectNames(session.getId());

    System.out.println("res ==>> " + subjectsName);
    System.out.println("apchu ............................................");
    subjectsName.forEach(System.out::println);
    System.out.println("............................................");

    model.addAttribute("subjectsName", subjectsName);
    return "studentPages/my-courses";
}

    @GetMapping("/schedule")
    @PreAuthorize("hasRole('STUDENT')")
    public ModelAndView schedule(Model model) throws JsonProcessingException {
        List<UserLessonsDTO> userLessons = userService.getUserLessons(session.getId());
        ModelAndView modelAndView = new ModelAndView("studentPages/schedule");
        modelAndView.addObject("lessons", userLessons);
        return modelAndView;
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
//    @PreAuthorize("hasRole('STUDENT')")
    public String info(Model model) {
        model.addAttribute("user", userService.findById().get());
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

    @PostMapping("/editAddress")
    @PreAuthorize("hasRole('STUDENT')")
    public String editAddress(@RequestParam String country,
                              @RequestParam String region,
                              @RequestParam String city,
                              @RequestParam String street) {
        Address address = Address.childBuilder()
                .city(city)
                .country(country)
                .street(street)
                .region(region)
                .build();
        Integer id = session.getId();
        userService.updateAddress(id, address);
        return "studentPages/main";
    }

    @PostMapping("/editUsername")
    @PreAuthorize("hasRole('STUDENT')")
    public String editUsername(@RequestParam String username) {
        Integer id = session.getId();
        userService.updateUsername(id, username);
        return "studentPages/main";
    }


//    usersession.getuserId

    @ExceptionHandler(JsonProcessingException.class)
    public ModelAndView jsonProcessingException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cause", "not.correct.data");
        modelAndView.addObject("prev", "/home");
        modelAndView.addObject("error_code", "error_code_400");
        modelAndView.setViewName("/errorPages/generalErrorPage");
        return modelAndView;
    }
}
