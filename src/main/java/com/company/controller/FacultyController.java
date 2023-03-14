package com.company.controller;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.dto.facultyDTO.DeleteFacultyDTO;
import com.company.dto.facultyDTO.UpdateFacultyDTO;
import com.company.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(Model model) {
        model.addAttribute("faculty", new Faculty());
        model.addAttribute("faculties", facultyService.findAll());
        return "crud/faculty/main";
    }


    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
    public String create(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "crud/faculty/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
    public String createPost(@Valid @ModelAttribute("faculty") CreateFacultyDTO faculty, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/faculty/create";
        }
        facultyService.create(faculty);
        return "redirect:/admin/faculty";
    }


    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update() {
        return "crud/faculty/main";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@Valid @ModelAttribute("faculty") UpdateFacultyDTO facultyDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/faculty/main";
        }
        facultyService.update(facultyDTO);
        return "redirect:/admin/faculty";

    }


    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete() {
        return "redirect:/admin/faculty";

    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@Valid @ModelAttribute("faculty") DeleteFacultyDTO facultyDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/faculty/main";
        }
        facultyService.delete(facultyDTO);
        return "redirect:/admin/faculty";
    }
}
