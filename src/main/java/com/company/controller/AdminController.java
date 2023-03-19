package com.company.controller;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.studentDTO.AddStudentDTO;
import com.company.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        model.addAttribute("pages", students.getTotalPages());
        model.addAttribute("groupId", groupId);
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

    @GetMapping("/searchStudent")
    public String searchStudentPage(@RequestParam(required = false) Integer pg, @RequestParam(required = false) String username,
                                    Model model){
        model.addAttribute("students", null);

        if ( Objects.nonNull(pg)) {
            getStudents(model, username, pg);
        }

        return "adminPages/searchStudent";
    }

    @PostMapping("/searchStudent")
    public String searchStudent(@RequestParam(required = false) Integer pg, @RequestParam(required = false) String username,
                                Model model){
        if ( Objects.isNull(username) || username.isBlank() ) {
            model.addAttribute("blank", "field.blank");
            return "adminPages/searchStudent";
        }

        getStudents(model, username, pg);

        return "adminPages/searchStudent";
    }

    @PostMapping("/addStudentGroup")
    public String addStudent(@RequestParam(required = false) Integer userId,
                             @RequestParam Integer groupId, Model model){
        service.addGroup(userId, groupId);
        model.addAttribute("save", true);
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
