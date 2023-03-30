package com.example.demo.controller;


import com.example.demo.model.Plant;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoginService;
import com.example.demo.model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String LoadLandingPage(Model model, HttpSession session) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @PostMapping("/createUser")
    public String postRegistration(@ModelAttribute Admin admin){
        admin.setPassword(encoder.encode(admin.getPassword()));
        loginService.addUser(admin);
        System.out.println("Funkar detta??");
        return "redirect:/login";
    }

    @PostMapping("/")
    public String postLogin(Model model, HttpSession session, @RequestParam String email,
                                  @RequestParam String password) {
        for (Admin admin : loginService.getUsers()) {
            if (email.equals(admin.getEmail())) {
                session.setAttribute("userId", admin.getId());
            }
        }
        return "home";
    }

    @GetMapping("/home")
    public String LoadHomePage(Model model) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentPrincipalName = authentication.getName();
       Admin admin = userRepository.findByEmail(currentPrincipalName);
        model.addAttribute("now", LocalDate.now());
       model.addAttribute("admin", admin);
        return "home";}


    //Home to Plantdescription
      @GetMapping("/plant/{id}")
    public String PlantDescription(Model model, @PathVariable Long id) {
        Plant plant = plantRepository.findById(id).get();
        model.addAttribute("plant", plant);
        return "plantdescription";
    }

    @GetMapping("/add")
    public String addPlant(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Admin admin = userRepository.findByEmail(currentPrincipalName);
        model.addAttribute("userId", admin.getId());
        model.addAttribute("plant", new Plant());
        return "plantform";
    }

    @PostMapping("/save")
    public String savePlant(@ModelAttribute Plant plant, HttpSession session, Model model){
        Long userId = (Long)session.getAttribute("userId");
        model.addAttribute("userId", 3L);
        plantRepository.save(plant);
        return "redirect:/home";
    }

}
