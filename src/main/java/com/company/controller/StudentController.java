package com.company.controller;

import com.company.domain.basicsOfBasics.Address;
import com.company.dto.studentDTO.UserUpdateDTO;
import com.company.repository.UserRepository;
import com.company.security.UserSession;
import com.company.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final UserService userService;
    private final UserSession userSession;
    private final UserRepository userRepository;

    public StudentController(UserService userService,
                             UserSession userSession, UserRepository userRepository) {
        this.userService = userService;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    @GetMapping("/main")
    @PreAuthorize("hasRole('STUDENT')")
    public String main(){
        return "studentPages/main";
    }

    @GetMapping("/news")
    @PreAuthorize("hasRole('STUDENT')")
    public String news(){
        return "studentPages/news";
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public String myCourses(){
        return "studentPages/my-courses";
    }

    @GetMapping("/schedule")
    @PreAuthorize("hasRole('STUDENT')")
    public String schedule(){
        return "studentPages/schedule";
    }

    @GetMapping("/retake")
    @PreAuthorize("hasRole('STUDENT')")
    public String retake(){
        return "studentPages/retake";
    }

    @GetMapping("/finals")
    @PreAuthorize("hasRole('STUDENT')")
    public String finals(){
        return "studentPages/finals";
    }

    @GetMapping("/study-plan")
    @PreAuthorize("hasRole('STUDENT')")
    public String studyPlan(){
        return "studentPages/study-plan";
    }

    @GetMapping("/info")
    @PreAuthorize("hasRole('STUDENT')")
    public String main(Model model) {
        model.addAttribute("user", userService.findById().get());
        return "studentPages/info";}

    @GetMapping("/survey")
    @PreAuthorize("hasRole('STUDENT')")
    public String survey(){
        return "studentPages/survey";
    }

    @GetMapping("/certificate")
    @PreAuthorize("hasRole('STUDENT')")
    public String certificate(){
        return "studentPages/certificate";
    }

    @GetMapping("/olympiad")
    @PreAuthorize("hasRole('STUDENT')")
    public String olympiad(){
        return "studentPages/olympiad";
    }

    @GetMapping("/diploma-work")
    @PreAuthorize("hasRole('STUDENT')")
    public String diplomaWork(){
        return "studentPages/diploma-work";
    }

    @GetMapping("/subject")
    @PreAuthorize("hasRole('STUDENT')")
    public String subject(){
        return "studentPages/subject";
    }

    @PostMapping("/editAddress")
    @PreAuthorize("hasRole('STUDENT')")
    public String editAddress(@RequestParam String country,
                              @RequestParam String region,
                              @RequestParam String city,
                              @RequestParam String street ){
        Address address = Address.childBuilder()
                .city(city)
                .country(country)
                .street(street)
                .region(region)
                .build();
        Integer id = userSession.getId();
        userService.updateAddress(id, address);
    return "studentPages/main";
    }
    @PostMapping("/editUsername")
    @PreAuthorize("hasRole('STUDENT')")
    public String editUsername(@RequestParam String username ){
        Integer id = userSession.getId();
        userService.updateUsername(id, username);
    return "studentPages/main";
    }


//    usersession.getuserId

}
