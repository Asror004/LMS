package com.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/error")
public class ErrorController {


    @RequestMapping("/403")
    public String error403Page(Model model) {
        return "errorPages/error403";
    }

    @RequestMapping("/404")
    public String error404Page(Model model) {
        return "errorPages/error404";
    }

    @RequestMapping("/500")
    public String error500Page(Model model) {
        return "errorPages/error500";
    }

    @RequestMapping("/503")
    public String error503Page(Model model) {
        return "errorPages/error503";
    }

    @RequestMapping("/504")
    public String error504Page(Model model) {
        return "errorPages/error504";
    }

    @RequestMapping("/505")
    public String error505Page(Model model) {
        return "errorPages/error505";
    }

    @RequestMapping("/506")
    public String error506Page(Model model) {
        return "errorPages/error506";
    }

    @RequestMapping("/507")
    public String error507Page(Model model) {
        return "errorPages/error507";
    }

    @RequestMapping("/508")
    public String error508Page(Model model) {
        return "errorPages/error508";
    }

    @RequestMapping("/509")
    public String error509Page(Model model) {
        return "errorPages/error509";
    }

    @RequestMapping("/510")
    public String error510Page(Model model) {
        return "errorPages/error510";
    }

    @RequestMapping("/511")
    public String error511Page(Model model) {
        return "errorPages/error511";
    }

    @RequestMapping("/512")
    public String error512Page(Model model) {
        return "errorPages/error512";
    }

    @RequestMapping("/513")
    public String error513Page(Model model) {
        return "errorPages/error513";
    }

    @RequestMapping("/514")
    public String error514Page(Model model) {
        return "errorPages/error514";
    }

    @RequestMapping("/515")
    public String error515Page(Model model) {
        return "errorPages/error515";
    }

    @RequestMapping("/516")
    public String error516Page(Model model) {
        return "errorPages/error516";
    }

    @RequestMapping("/517")
    public String error517Page(Model model) {
        return "errorPages/error517";
    }

    @RequestMapping("/518")
    public String error518Page(Model model) {
        return "errorPages/error518";
    }
}
