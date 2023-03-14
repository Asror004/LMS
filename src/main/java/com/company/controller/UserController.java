package com.company.controller;

import com.company.domain.basicsOfBasics.User;
import com.company.dto.userDTO.CreateUserDTO;
import com.company.security.UserSession;
import com.company.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final UserSession userSession;
    @GetMapping("/register")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_ADD_STUDENT_PERMISSION)")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "adminPages/registerStudent";
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_ADD_STUDENT_PERMISSION)")
    public String register(@Valid @ModelAttribute("user") CreateUserDTO dto, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "adminPages/registerStudent";
        }

        if (service.hasPassport(dto.passport())) {
            errors.rejectValue("passport", "", "field.exist");
            return "adminPages/registerStudent";
        }
        service.save(dto);
        model.addAttribute("save", true);
        return "adminPages/registerStudent";
    }
}
