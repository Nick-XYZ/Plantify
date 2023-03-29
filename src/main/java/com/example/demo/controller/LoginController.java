package com.example.demo.controller;


import com.example.demo.service.LoginService;
import com.example.demo.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/")
    public String LoadLandingPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/")
    public String postLogin(Model model, HttpSession session, @RequestParam String username,
                                  @RequestParam String password) {
                     return "/home";
            }
        }
                return "login";

    }

   /* @PostMapping("/createUser")
    public String postRegistration(HttpSession session, @RequestParam String email, @RequestParam String password){
        User user = new User(email, encoder.encode(password));
        loginService.addUser(user);
        return "login";
    }*/

    @GetMapping("/home")
    public String LoadHomePage(){return "home";}

}
