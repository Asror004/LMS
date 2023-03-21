package com.company.controller;

import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.groupDTO.CreateGroupDTO;
import com.company.dto.groupDTO.*;
import com.company.dto.teacherDTO.AllTeachersWithNamesDTO;
import com.company.repository.TeacherRepository;
import com.company.service.FacultyService;
import com.company.service.GroupService;
import com.company.service.TeacherService;
import com.company.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/group")
public class GroupController {
    private final GroupService groupService;
    private final FacultyService facultyService;
    private final TeacherService teacherService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("groups", groupService.findAll());
        return "crud/group/main";
    }


    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_GROUP_PERMISSION)")
    public String create(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("faculties", facultyService.findAll());
        List<AllTeachersWithNamesDTO> teachers = new ArrayList<>();
        for (Teacher teacher : teacherService.findAll()) {
            User user = userService.findById(teacher.getUser_id());
            teachers.add(new AllTeachersWithNamesDTO(teacher.getUser_id(), user.getFirstName(),user.getLastName()));
        }
        System.out.println("teachers = " + teachers);
        model.addAttribute("teachers", teachers);
        return "crud/group/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_GROUP_PERMISSION)")
    public String createPost(@Valid @ModelAttribute("group")CreateGroupDTO groupDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/group/create";
        }
        groupService.create(groupDTO);
        return "redirect:/admin/group";
    }


    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update() {
        return "crud/group/main";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@Valid @ModelAttribute("group") UpdateGroupDTO groupDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/group/main";
        }
        groupService.update(groupDTO);
        return "redirect:/admin/group";

    }
    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete() {
        return "redirect:/admin/group";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@Valid @ModelAttribute("group") DeleteGroupDTO groupDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/group/main";
        }
        groupService.delete(groupDTO);
        return "redirect:/admin/group";
    }
}
