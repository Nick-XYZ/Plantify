package com.example.demo.controller;


import com.example.demo.model.Plant;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.service.LoginService;
import com.example.demo.model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/")
    public String LoadLandingPage(Model model) {
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
        return "home";
    }

    @GetMapping("/home")
    public String LoadHomePage(){return "home";}


    //Home to Plantdescription
      @GetMapping("/plant/{id}")
    public String PlantDescription(Model model, @PathVariable Long id) {
        Plant plant = plantRepository.findById(id).get();
        model.addAttribute("plant", plant);
        return "plantdescription";
    }

}
