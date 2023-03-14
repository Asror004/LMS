//package com.company.controller;
//
//import com.company.domain.basic.Faculty;
//import com.company.domain.basic.Room;
//import com.company.dto.facultyDTO.CreateFacultyDTO;
//import com.company.dto.facultyDTO.DeleteFacultyDTO;
//import com.company.dto.facultyDTO.UpdateFacultyDTO;
//import com.company.dto.roomDTO.CreateRoomDTO;
//import com.company.dto.roomDTO.DeleteRoomDTO;
//import com.company.repository.RoomRepository;
//import com.company.service.FacultyService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/admin/room")
//public class RoomController {
//    private final RoomRepository roomRepository;
//
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public String main(Model model) {
//        model.addAttribute("room", new Room());
//        model.addAttribute("rooms", roomRepository.findAll());
//        return "crud/room/main";
//    }
//
//
//    @GetMapping("/create")
//    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
//    public String create(Model model) {
//        model.addAttribute("room", new Faculty());
//        model.addAttribute("enter.smth");
//        model.addAttribute("name.name");
//        model.addAttribute("create.name");
//        model.addAttribute("name.room");
//        model.addAttribute("name.create");
//        return "crud/room/create";
//    }
//
//    @PostMapping("/create")
//    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_FACULTY_PERMISSION)")
//    public String createPost(@Valid @ModelAttribute("room") CreateRoomDTO room, BindingResult errors) {
//        if (errors.hasErrors()) {
//            return "crud/room/create";
//        }
//        roomRepository.create(room);
//        return "redirect:/admin/room";
//    }
//
//
//    @GetMapping("/update")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String update() {
//        return "crud/room/main";
//    }
//
//    @PostMapping("/update")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String updatePost(@Valid @ModelAttribute("faculty") UpdateFacultyDTO facultyDTO, BindingResult errors) {
//        if (errors.hasErrors()) {
//            return "crud/room/main";
//        }
//        roomRepository.update(facultyDTO);
//        return "redirect:/admin/room";
//
//    }
//
//
//    @GetMapping("/delete")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String delete() {
//        return "redirect:/admin/room";
//
//    }
//
//    @PostMapping("/delete")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String deletePost(@Valid @ModelAttribute("faculty") DeleteRoomDTO roomDTO, BindingResult errors) {
//        if (errors.hasErrors()) {
//            return "crud/room/main";
//        }
//        roomRepository.delete(roomDTO);
//        return "redirect:/admin/room ";
//    }
//}
