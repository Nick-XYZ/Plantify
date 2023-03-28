package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlantController {


    @GetMapping("/home")
    public String login() {
        return "home";
    }
}
