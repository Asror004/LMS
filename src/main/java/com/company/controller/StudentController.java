package com.company.controller;

import com.company.domain.basic.Attendance;
import com.company.domain.basic.Lesson;
import com.company.domain.basic.News;
import com.company.dto.attendanceDTO.AttendanceAndClassesDTO;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.repository.*;
import com.company.security.UserSession;
import com.company.domain.basicsOfBasics.Address;
import com.company.dto.studentDTO.UserUpdateDTO;
import com.company.repository.UserRepository;
import com.company.security.UserSession;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final UserSession session;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final AttendanceRepository attendanceRepository;
    private final NewsRepository newsRepository;

    private final UserService userService;
    private final UserSession userSession;
    private final UserRepository userRepository;


    @GetMapping("/main")
    @PreAuthorize("hasRole('STUDENT')")
    public String main() {
        return "studentPages/main";
    }

    @GetMapping("/news")
    @PreAuthorize("hasRole('STUDENT')")
    public String news(Model model) {

        List<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "studentPages/news";
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
    public String info(Model model) {
        model.addAttribute("user", userService.findById().get());
        return "studentPages/info";}

    @GetMapping("/survey")
    @PreAuthorize("hasRole('STUDENT')")
    public String survey() {
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

    @PostMapping("/editAddress")
    @PreAuthorize("hasRole('STUDENT')")
    public String editAddress(@RequestParam String country,
                              @RequestParam String region,
                              @RequestParam String city,
                              @RequestParam String street ){
        Address address = Address.childBuilder()
                .city(city)
                .country(country)
                .street(street)
                .region(region)
                .build();
        Integer id = userSession.getId();
        userService.updateAddress(id, address);
    return "studentPages/main";
    }
    @PostMapping("/editUsername")
    @PreAuthorize("hasRole('STUDENT')")
    public String editUsername(@RequestParam String username ){
        Integer id = userSession.getId();
        userService.updateUsername(id, username);
    return "studentPages/main";
    }


//    usersession.getuserId

}
