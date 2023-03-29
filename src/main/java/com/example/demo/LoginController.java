package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private SpeciesRepository speciesRepository;

    @GetMapping("/")
    public String login() {
        return "Login";
    }

    @GetMapping("/plant")
    public String plant() {
        return "plantdescription";
    }

    @GetMapping("/plant/{id}")
    public String plantID(Model model, @PathVariable Long id) {
        Plant plant = plantRepository.findById(id).get();
        model.addAttribute("plant", plant);
        return "plantdescription";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
