package com.company.controller;

import com.company.domain.basic.Lesson;
import com.company.dto.attendanceDTO.AttendanceAndClassesDTO;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.dto.teacherDTO.UserLessonsDTO;
import com.company.repository.AttendanceRepository;
import com.company.repository.GroupRepository;
import com.company.repository.LessonRepository;
import com.company.repository.SubjectRepository;
import com.company.security.UserSession;
import com.company.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final UserSession session;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;

    public StudentController(UserSession session, SubjectRepository subjectRepository,
                             LessonRepository lessonRepository,
                             GroupRepository groupRepository,
                             AttendanceRepository attendanceRepository, UserService userService) {
        this.session = session;
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.attendanceRepository = attendanceRepository;
        this.userService = userService;
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
