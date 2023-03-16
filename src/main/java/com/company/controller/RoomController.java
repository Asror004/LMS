package com.company.controller;

import com.company.domain.basic.Room;
import com.company.dto.roomDTO.*;
import com.company.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String main(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomService.findAll());
        return "crud/room/main";
    }


    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_ROOM_PERMISSION)")
    public String create(Model model) {
        model.addAttribute("room", new Room());
        return "crud/room/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority(T(com.company.permissions.AdminPermissions).HAS_CREATE_ROOM_PERMISSION)")
    public String createPost(@RequestParam String name, @RequestParam Integer count,Model model) {
        if (name.isBlank()) {
            model.addAttribute("field","field.blank");
            return "crud/room/create";
        }
        if (name.length()>10) {
            model.addAttribute("field","Must be max 10");
            return "crud/room/create";
        }
        if (count<1) {
            model.addAttribute("count","must.positive");
            return "crud/room/create";
        }
//        TODO: CHECK IS ALREADY EXISTS
//        Room byName = roomService.findByName(name.toUpperCase() + "-" + count);
//        System.out.println(byName);
//        if (byName != null) {
//            model.addAttribute("field","room.exists");
//        }


        roomService.create(name,count);
        return "redirect:/admin/room";
    }


    @GetMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update() {
        return "crud/room/main";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePost(@Valid @ModelAttribute("room") DeleteRoomDTO roomDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/room/main";
        }
        roomService.update(roomDTO);
        return "redirect:/admin/room";

    }
    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete() {
        return "redirect:/admin/room";

    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePost(@Valid @ModelAttribute("room") DeleteRoomDTO roomDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return "crud/room/main";
        }
        roomService.delete(roomDTO);
        return "redirect:/admin/room";
    }
}
