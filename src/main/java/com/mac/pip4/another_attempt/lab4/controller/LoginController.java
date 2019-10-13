package com.mac.pip4.another_attempt.lab4.controller;


import com.mac.pip4.another_attempt.lab4.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private CheckRepository checkRepository;

    @Autowired
    public LoginController(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @RequestMapping("/login")
    public String login(Model model
//                        @RequestParam(value = "error", required = false) String error
    ) {
//
//        Map<Object, Object> dataForFront = new HashMap<>();
//        dataForFront.put("error", error == null ? "" : "Try again please");
//
//        model.addAttribute("magic_data", dataForFront);
        return "login";

    }

    @RequestMapping("/")
    public String home(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<Object, Object> dataForFront = new HashMap<>();
        dataForFront.put("results", checkRepository.findByShooterOrderByCheckId(auth.getName()));

        model.addAttribute("magic_data", dataForFront);

        return "index";
    }

}
