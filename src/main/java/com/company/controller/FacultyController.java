package com.company.controller;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(){
        return "faculty/main";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculty/create";
    }
//    @PostMapping("/blog")
//    public String saveBlog(@Valid @ModelAttribute("blog") Blog blog, BindingResult errors, RedirectAttributes redirectAttributes) {
////        redirectAttributes.addFlashAttribute("msg", "dfsljfs");
////        return "redirect:/template";
//        if (errors.hasErrors()) {
//            return "blog";
//        }
//        if (!blog.getTitle().equals(blog.getTitle2())) {
//            errors.rejectValue("title", "", "fields.did.not.match.each.other");
//            errors.rejectValue("title2", "", "fields.did.not.match.each.other");
//            return "blog";
//        }
//        System.out.println("blog = " + blog);
//        return "redirect:/blog";
//    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createPost(@Valid @ModelAttribute("faculty") CreateFacultyDTO faculty, BindingResult errors, RedirectAttributes redirectAttributes){
        if (errors.hasErrors()) {
            return "faculty/create";
        }
        return "redirect:/faculty";
    }
    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(){
        return "faculty/update";
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(){
        return "faculty/update";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(){
        return "faculty/delete";
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(){
        return "faculty/delete";
    }


}
