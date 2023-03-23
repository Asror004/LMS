package com.company.controller;

import com.company.domain.auth.AuthRole;
import com.company.domain.basic.Subject;
import com.company.dto.subjectDTO.CreateSubjectDTO;
import com.company.dto.subjectDTO.DeleteSubjectDTO;
import com.company.dto.subjectDTO.UpdateSubjectDTO;
import com.company.exceptions.PermissionDeniedException;
import com.company.repository.auth.AuthRoleRepository;
import com.company.security.UserSession;
import com.company.service.SubjectService;
import com.company.service.UserService;
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

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/subject")
public class SubjectController {
    private final SubjectService subjectService;
    private final UserService authUserService;
    private final AuthRoleRepository authRoleRepository;
    private final UserSession session;



    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public String main(Model model, Principal principal) {
        String name = principal.getName();
        AuthRole admin = authRoleRepository.findByCode("ADMIN").orElseThrow();
        AuthRole teacher = authRoleRepository.findByCode("TEACHER").orElseThrow();

        if ( session.getUser().getAuthRoles().contains(admin) ) {
            model.addAttribute("subject", new Subject());
            model.addAttribute("subjects", subjectService.findAll());
            return "crud/subject/main";
        }
        throw new PermissionDeniedException("You don't have permission to access this page");

    }


    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
    public String create(Model model) {
        model.addAttribute("subject", new Subject());
        return "crud/subject/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
    public String createPost(@Valid @ModelAttribute("subject") CreateSubjectDTO subject, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/subject/create";
        }
        subjectService.create(subject);
        return "redirect:/admin/subject";
    }


    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update() {
        return "crud/subject/main";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@Valid @ModelAttribute("subject") UpdateSubjectDTO subjectDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/subject/main";
        }
        subjectService.update(subjectDTO);
        return "redirect:/admin/subject";

    }


    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete() {
        return "redirect:/admin/subject";

    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@Valid @ModelAttribute("subject") DeleteSubjectDTO subjectDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/subject/main";
        }
        subjectService.delete(subjectDTO);
        return "redirect:/admin/subject";
    }
}
