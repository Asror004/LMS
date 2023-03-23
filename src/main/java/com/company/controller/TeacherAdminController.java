package com.company.controller;

import com.company.domain.basic.Lesson;
import com.company.domain.basic.News;
import com.company.domain.basicsOfBasics.Address;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.attendanceDTO.AttendanceAndClassesDTO;
import com.company.dto.attendanceDTO.AttendanceByLessonIdDTO;
import com.company.dto.studentDTO.CreateStudentDTO;
import com.company.dto.teacherDTO.CreateTeacherDTO;
import com.company.dto.teacherDTO.UserLessonsDTO;
import com.company.repository.*;
import com.company.security.UserSession;
import com.company.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/teacher")
@RequiredArgsConstructor
public class TeacherAdminController {
    private final UserService service;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final UserSession userSession;
    @GetMapping("/register")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_ADD_STUDENT_PERMISSION)")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "crud/teacher/registerTeacher";
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_ADD_STUDENT_PERMISSION)")
    public String register(@Valid @ModelAttribute("user") CreateTeacherDTO dto, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "crud/teacher/registerTeacher";
        }

        if (service.hasPassport(dto.passport())) {
            errors.rejectValue("passport", "", "field.exist");
            return "crud/teacher/registerTeacher";
        }
        service.saveTeacher(dto);
        model.addAttribute("save", true);
        return "crud/teacher/registerTeacher";
    }
}
