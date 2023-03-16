package com.company.controller;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.AddStudentDTO;
import com.company.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService service;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(){
        return "adminPages/main";
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String pg, @RequestParam(required = false) Integer groupId,
                       Model model){
        Page<User> students = service.getStudents(pg, groupId);

        model.addAttribute("students", students.getContent());
        model.addAttribute("groupId", groupId);
        model.addAttribute("pages", students.getTotalPages());
        model.addAttribute("hasNext", students.hasNext());
        model.addAttribute("current", students.getNumber());
        model.addAttribute("hasPrevious", students.hasPrevious());
        return "adminPages/studentList";
    }

    @GetMapping("/groupSearch")
    public String groupSearchPage(Model model, @RequestParam(required = false) String group, @RequestParam(required = false) String pg){
        model.addAttribute("groups", null);

        if ( Objects.nonNull(pg)) {
            getGroups(model, group, pg);
        }
        return "adminPages/searchGroup";
    }

    private void getGroups(Model model, String group, String pg) {
        Page<Group> groups = service.getGroups(group, pg);
        model.addAttribute("groups", groups.getContent());
        model.addAttribute("name", group);
        model.addAttribute("pages", groups.getTotalPages());
        model.addAttribute("hasNext", groups.hasNext());
        model.addAttribute("current", groups.getNumber());
        model.addAttribute("hasPrevious", groups.hasPrevious());
    }

    @PostMapping("/groupSearch")
    public String groupSearch(Model model, @RequestParam(required = false) String group){
        if (Objects.isNull(group) || group.isBlank()) {
            model.addAttribute("blank","field.blank");
            return "adminPages/searchGroup";
        }
        getGroups(model, group, null);

        model.addAttribute("name", group);
        return "adminPages/searchGroup";
    }

    @GetMapping("/addStudentGroup")
    public String searchStudentPage(@ModelAttribute AddStudentDTO dto, Model model){
        model.addAttribute("groupId", dto.groupId());

        model.addAttribute("students", null);

        if ( Objects.nonNull(dto.pg())) {
            getStudents(model, dto.username(), dto.pg());
        }

        return "adminPages/searchStudent";
    }

    @PostMapping("/addStudentGroup")
    public String searchStudent(@ModelAttribute AddStudentDTO dto, Model model){
        if ( Objects.isNull(dto.username()) || dto.username().isBlank() ) {
            model.addAttribute("blank", "field.blank");
            return "adminPages/searchStudent";
        }

        getStudents(model, dto.username(), dto.pg());
        model.addAttribute("groupId", dto.groupId());

        return "adminPages/searchStudent";
    }

    private void getStudents(Model model, String username, Integer pg) {
        Page<AuthUser> users = service.getStudentsByUsername(pg, username);
        model.addAttribute("users", users.getContent());
        model.addAttribute("username", username);
        model.addAttribute("pages", users.getTotalPages());
        model.addAttribute("hasNext", users.hasNext());
        model.addAttribute("current", users.getNumber());
        model.addAttribute("hasPrevious", users.hasPrevious());
    }
}
