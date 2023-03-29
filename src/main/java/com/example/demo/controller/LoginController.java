package com.example.demo.controller;


import com.example.demo.service.LoginService;
import com.example.demo.model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/")
    public String LoadLandingPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

   /* @GetMapping("/registration")
    public String LoadRegistration(Model model){
        model.addAttribute("admin", new Admin());
        return "registration";
    }*/

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

}
