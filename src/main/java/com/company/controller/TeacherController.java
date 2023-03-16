package com.company.controller;

import com.company.domain.basic.Lesson;
import com.company.dto.teacherDTO.StudentsInLessonsDTO;
import com.company.dto.teacherDTO.UserDetailForAttendanceDTO;
import com.company.dto.teacherDTO.WeeklyLessonsDetail;
import com.company.security.UserSession;
import com.company.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String homePage(){
        return "teacherPages/main";
    }
    @PostMapping("/complete-lesson")
    @PreAuthorize("hasRole('TEACHER')")
    public ModelAndView completeLessonPage(@RequestParam(name = "lesson_id") String lessonId,
                                           @RequestParam(name = "monday_date", required = false) String monday,
                                           @RequestParam(name = "day_off_week") String dayOffWeek){
        String day;
        if(monday==null){
            day=LocalDate.now().toString();
        }else {
            day=LocalDate.parse(monday, DateTimeFormatter.ISO_DATE).plusDays(Long.parseLong(dayOffWeek)).toString();
        }
        StudentsInLessonsDTO list = teacherService.getUsersByLessonId(lessonId);
        list.setDate(day);
        ModelAndView modelAndView = new ModelAndView("/teacherPages/complete_lessons");
        modelAndView.addObject("list",list);
        return modelAndView;
    }


    @GetMapping("/attendance")
    @PreAuthorize("hasRole('TEACHER')")
    public ModelAndView attendance(@RequestParam(name = "monday_date", required = false) String mondayDate){
        if(mondayDate==null){
            mondayDate = String.valueOf(LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue()-1));
        }
        List<WeeklyLessonsDetail> weeklyLessonsDetailsByTeacherId = teacherService.getWeeklyLessonsDetailsByTeacherId(userSession.getId(), mondayDate);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/teacherPages/attendance");
        modelAndView.addObject("lessons",weeklyLessonsDetailsByTeacherId);
        modelAndView.addObject("monday_date",mondayDate);
        return modelAndView;
    }



//    @ExceptionHandler(Exception.class)
//    public String forbidden(Exception e){
//        System.out.println(Arrays.toString(e.getStackTrace()));
//        return "/errorPages/forbidden";
//    }
}