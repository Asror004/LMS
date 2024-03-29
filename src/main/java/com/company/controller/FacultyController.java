package com.company.controller;

import com.company.domain.auth.AuthRole;
import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.dto.facultyDTO.DeleteFacultyDTO;
import com.company.dto.facultyDTO.UpdateFacultyDTO;
import com.company.exceptions.PermissionDeniedException;
import com.company.repository.GroupRepository;
import com.company.repository.UserRepository;
import com.company.repository.auth.AuthRoleRepository;
import com.company.repository.auth.AuthUserRepository;
import com.company.security.UserSession;
import com.company.service.FacultyService;
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
@RequestMapping("/admin/faculty")
public class FacultyController {
    private final FacultyService facultyService;
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
            model.addAttribute("faculty", new Faculty());
            model.addAttribute("faculties", facultyService.findAll());
            return "crud/faculty/main";
        }
        throw new PermissionDeniedException("You don't have permission to access this page");

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
