package com.example.demo.controller;


import com.example.demo.model.Plant;
import com.example.demo.model.PlantLog;
import com.example.demo.repository.PlantLogRepository;
import com.example.demo.repository.PlantRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoginService;
import com.example.demo.model.Admin;
import com.example.demo.service.PlantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

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
    @Autowired
    PlantService plantService;
    @Autowired
    PlantLogRepository plantLogRepository;


    @GetMapping("/")
    public String LoadLandingPage(Model model, HttpSession session) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @PostMapping("/createUser")
    public RedirectView postRegistration(@ModelAttribute Admin admin, RedirectAttributes redir) {
        try {
            RedirectView rvLogin = new RedirectView("/", true);
            //If user already exists.. Model.addAttribute (first Validation exercise)
            admin.setPassword(encoder.encode(admin.getPassword()));
            if (userRepository.findByEmail(admin.getEmail()) != null) {
                throw new SQLIntegrityConstraintViolationException("Email allready exists");
            }
            loginService.addUser(admin);
            redir.addFlashAttribute("NewAccountSuccess", "Your registration is confirmed.");

            return rvLogin;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email allready taken");
            RedirectView rvLogin = new RedirectView("/", true);
            //If user already exists.. Model.addAttribute (first Validation exercise)
            redir.addFlashAttribute("EmailAlreadyTaken", "There is already an account with that email");
            return rvLogin;
        }
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
        Admin admin = getLoggedInAdmin();
        List<Plant> userPlants = plantRepository.findAllByAdminId(admin.getId());
        // for loop av userplants för att få med in i plants
        for (Plant plant : userPlants) {
            plant.setDoTask(plantService.eventDayValue(plant.getId()));
        }
        model.addAttribute("watercount", plantService.counter(admin, "water"));
        model.addAttribute("nutritioncount", plantService.counter(admin, "nutrition"));
        model.addAttribute("repotcount", plantService.counter(admin, "repot"));
        model.addAttribute("admin", admin);
        model.addAttribute("plants", userPlants);
        model.addAttribute("userId", admin.getId());
        model.addAttribute("plant", new Plant());
        return "home";
    }

    //Home to Plantdescription
    @GetMapping("/plant/{id}")
    public String PlantDescription(Model model, @PathVariable Long id) {
        Admin admin = getLoggedInAdmin();
        List<Plant> userPlants = plantRepository.findAllByAdminId(admin.getId());
        Plant plant = plantRepository.findById(id).get();
       if (userPlants.contains(plant)) {
           for (Plant plantEvent : userPlants) {
               plantEvent.setDoTask(plantService.eventDayValue(plantEvent.getId()));}
            model.addAttribute("today", LocalDate.now());
            model.addAttribute("eventDayValue", plantService.eventDayValue(id));
            model.addAttribute("plant", plant);
            model.addAttribute("admin", admin);
            model.addAttribute("timeline", plantService.nextFiveTimeline(id));
            return "plantdescription";
        }
        else {
            return "redirect:/home";
        }
    }

    @GetMapping("/createPlantLog/{event}/{id}")
    public String plantLog(@PathVariable String event, @PathVariable Long id) {
        PlantLog plantLog = new PlantLog();
        plantLog.setPlant(plantRepository.findById(id).get());
        plantLog.setEvent(event);
        plantLogRepository.save(plantLog);
        return "redirect:/plant/" + id;
    }

    @GetMapping("/doAll/{event}")
    public String waterAll2(@PathVariable String event) {
        Admin admin = getLoggedInAdmin();
        List<Plant> userPlants = plantRepository.findAllByAdminId(admin.getId());
        for (Plant plant : userPlants) {
            plant.setDoTask(plantService.eventDayValue(plant.getId()));
            if (plant.getDoTask().contains(event)) {
                PlantLog plantLog = new PlantLog();
                plantLog.setPlant(plant);
                plantLog.setEvent(event);
                plantLogRepository.save(plantLog);
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/save")
    public String savePlant(@ModelAttribute Plant plant, HttpSession session, Model model, RedirectAttributes ra) {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        plantRepository.save(plant);
        ra.addFlashAttribute("SuccesPlantCreation", "Your plant has been added to your collection.");
        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    private Admin getLoggedInAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Admin admin = userRepository.findByEmail(currentPrincipalName);
        return admin;
    }
    //Delete Plant
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,RedirectAttributes ra) throws Exception {
        Admin admin = getLoggedInAdmin();
        List<Plant> userPlants = plantRepository.findAllByAdminId(admin.getId());
        Plant plant = plantRepository.findById(id).get();
        List<PlantLog> plantLog = plantLogRepository.findAllByPlantId(plant.getId());
        if (userPlants.contains(plant)) {
            for (PlantLog pl : plantLog) {
                plantLogRepository.delete(pl);
            }
            plantService.deletePlant(id);
            ra.addFlashAttribute("SuccesPlantCreation", "Your plant has been removed");
            return "redirect:/home";
        } else {
            ra.addFlashAttribute("ErrorPlantRemoval", "No such plant");
            return "redirect:/home";
        }
    }

}
