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
import jakarta.validation.constraints.NotNull;
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
        model.addAttribute("group_d", new Group());
        model.addAttribute("group_u", new Group());
        model.addAttribute("groups", groupService.findAll());
        return "crud/group/main";
    }


    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_GROUP_PERMISSION)")
    public String create(Model model) {
            model.addAttribute("group", new Group());
        model.addAttribute("faculties", facultyService.findAll());
        List<AllTeachersWithNamesDTO> teachers = new ArrayList<>();
        teacherService.findAll().forEach(teacher -> {
            User user = userService.findById(teacher.getUser_id());
            teachers.add(new AllTeachersWithNamesDTO(teacher.getUser_id(), user.getFirstName(), user.getLastName()));
        });
        model.addAttribute("teachers", teachers);
        return "crud/group/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_GROUP_PERMISSION)")
    public String createPost(@Valid @ModelAttribute("group")CreateGroupDTO groupDTO,@RequestParam("faculty")Integer faculty,@RequestParam("owner")Integer owner, BindingResult errors) {
//        @NotNull(message = "Faculty is required")
//        Integer faculty,
//
////        @NotNull(message = "Teacher is required")
//        @NotNull(message = "Teacher is required")
//        Integer owner
        if (errors.hasErrors()) {
            return "crud/group/create";
        }
        groupService.create(groupDTO,faculty,owner);
        return "redirect:/admin/group";
    }

    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update() {
        return "crud/group/main";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@Valid @ModelAttribute("group_u") UpdateGroupDTO groupDTO, BindingResult errors) {
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
    public String deletePost(@Valid @ModelAttribute("group_d") DeleteGroupDTO groupDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/group/main";
        }
        groupService.delete(groupDTO);
        return "redirect:/admin/group";
    }
}
