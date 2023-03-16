//package com.company.controller;
//
//import com.company.domain.basic.Group;
//import com.company.service.GroupService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/admin/group")
//public class GroupController {
//    private final GroupService groupService;
//
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public String main(Model model) {
//        model.addAttribute("group", new Group());
//        model.addAttribute("groups", groupService.findAll());
//        return "crud/group/main";
//    }
//
//
//    @GetMapping("/create")
//    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_GROUP_PERMISSION)")
//    public String create(Model model) {
//        model.addAttribute("group", new Group());
//        return "crud/group/create";
//    }
//
//    @PostMapping("/create")
//    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_GROUP_PERMISSION)")
//    public String createPost(@RequestParam String name, @RequestParam Integer count,Model model) {
//        if (name.isBlank()) {
//            model.addAttribute("field","field.blank");
//            return "crud/group/create";
//        }
//        if (name.length()>10) {
//            model.addAttribute("field","Must be max 10");
//            return "crud/group/create";
//        }
//        if (count<1) {
//            model.addAttribute("count","must.positive");
//            return "crud/group/create";
//        }
////        TODO: CHECK IS ALREADY EXISTS
////        Group byName = groupService.findByName(name.toUpperCase() + "-" + count);
////        System.out.println(byName);
////        if (byName != null) {
////            model.addAttribute("field","group.exists");
////        }
//
//
//        groupService.create();
//        return "redirect:/admin/group";
//    }
//
//
//    @GetMapping("/update")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String update() {
//        return "crud/group/main";
//    }
//
//    @PostMapping("/update")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String updatePost(@Valid @ModelAttribute("group") DeleteGroupDTO groupDTO, BindingResult errors) {
//        if (errors.hasErrors()) {
//            return "crud/group/main";
//        }
//        groupService.update(groupDTO);
//        return "redirect:/admin/group";
//
//    }
//    @GetMapping("/delete")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String delete() {
//        return "redirect:/admin/group";
//
//    }
//
//    @PostMapping("/delete")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String deletePost(@Valid @ModelAttribute("group") DeleteGroupDTO groupDTO, BindingResult errors) {
//        if (errors.hasErrors()) {
//            return "crud/group/main";
//        }
//        groupService.delete(groupDTO);
//        return "redirect:/admin/group";
//    }
//}
