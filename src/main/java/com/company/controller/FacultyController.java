package com.company.controller;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.dto.facultyDTO.DeleteFacultyDTO;
import com.company.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(Model model){
        model.addAttribute("faculties", facultyService.findAll());
        return "faculty/main";
    }


    @GetMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
    public String create(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculty/create";
    }
    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
    public String createPost(@Valid @ModelAttribute("faculty") CreateFacultyDTO faculty, BindingResult errors){
        if (errors.hasErrors()) {
            return "faculty/create";
        }
        facultyService.create(faculty);
        return "redirect:/faculty";
    }


    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(){
        return "faculty/update";
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@ModelAttribute("f_id") DeleteFacultyDTO dto){
        System.out.println(dto.id());
        return "redirect:/faculty";
    }



    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam("f_id") String id){
        System.out.println(id);
        return "faculty/delete";
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(){
        return "redirect:/faculty";
    }




}
