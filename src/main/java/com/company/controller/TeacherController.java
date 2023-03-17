package com.company.controller;

import com.company.dto.studentDTO.StudentsForAttendanceDTO;
import com.company.dto.teacherDTO.StudentsInLessonsDTO;
import com.company.dto.teacherDTO.WeeklyLessonsDetail;
import com.company.security.UserSession;
import com.company.service.TeacherService;
import com.company.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/teacher")
@ComponentScan("com.company")
@RequiredArgsConstructor
public class TeacherController {

    private final UserSession userSession;
    private final TeacherService teacherService;

    @GetMapping("/home")
    @PreAuthorize("hasRole('TEACHER')")
    public String homePage() {
        return "teacherPages/main";
    }


    @GetMapping("/attendance")
    @PreAuthorize("hasRole('TEACHER')")
    public ModelAndView attendance(@RequestParam(name = "monday_date", required = false) String mondayDate) {
        LocalDate monday_date;
        if (mondayDate == null) {
            monday_date = LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() - 1);
        }else {
            monday_date=LocalDate.parse(mondayDate,DateTimeFormatter.ISO_DATE);
        }
        List<WeeklyLessonsDetail> weeklyLessonsDetailsByTeacherId = teacherService.getWeeklyLessonsDetailsByTeacherId(userSession.getId(), monday_date.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/teacherPages/attendance");
        modelAndView.addObject("lessons", weeklyLessonsDetailsByTeacherId);
        modelAndView.addObject("monday_date", monday_date);
        modelAndView.addObject("today", LocalDate.now());
        modelAndView.addObject("util", new DateUtils());
        return modelAndView;
    }


    @PostMapping("/complete-lesson")
    @PreAuthorize("hasRole('TEACHER')")
    public ModelAndView completeLessonPage(@RequestParam(name = "lesson_id") String lessonId,
                                           @RequestParam(name = "today", required = false) String day) throws JsonProcessingException {
        StudentsInLessonsDTO list = teacherService.getUsersByLessonId(lessonId);
        list.setDate(day);
        ModelAndView modelAndView = new ModelAndView("/teacherPages/complete_lessons");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @PostMapping("/complete-lesson-finish")
    @PreAuthorize("hasRole('TEACHER')")
    public ModelAndView completeLessonFinishPage(@Valid @ModelAttribute StudentsForAttendanceDTO studentsDTO) {
        List<Integer> studentIdsInGroup = teacherService.getStudentIdsInGroup(studentsDTO.getGroup_id());

        ModelAndView modelAndView = new ModelAndView("/teacherPages/attendance");
        modelAndView.addObject("list");
        return modelAndView;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView illegalArgumentException(Exception e){
        ModelAndView modelAndView = new ModelAndView("/errorPages/generalErrorPage");
        modelAndView.addObject("cause","teacher.users.not.found.by.group");
        modelAndView.addObject("prev","/teacher/attendance");
        modelAndView.addObject("error_code","error_code_400");
        return modelAndView;
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ModelAndView jsonProcessingException(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cause","not.correct.data");
        modelAndView.addObject("prev","/teacher/attendance");
        modelAndView.addObject("error_code","error_code_400");
        modelAndView.setViewName("/errorPages/generalErrorPage");
        return modelAndView;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView methodArgumentNotValidException(MethodArgumentNotValidException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cause","not.correct.data");
        modelAndView.addObject("prev","/teacher/complete-lesson");
        modelAndView.addObject("error_code","error_code_400");
        modelAndView.setViewName("/errorPages/generalErrorPage");
        return modelAndView;
    }
}