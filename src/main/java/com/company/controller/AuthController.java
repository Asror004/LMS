package com.company.controller;

import com.company.dto.AuthUserRegisterDTO;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "auth/logout";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute AuthUserRegisterDTO dto) {
        service.save(dto);
        return "redirect:/auth/login";
    }

}