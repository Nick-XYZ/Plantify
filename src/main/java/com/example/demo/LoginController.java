package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/")
    public String login() {
        return "Login";
    }

    @GetMapping("/plant")
    public String plant() {
        return "plantdescription";
    }
}
