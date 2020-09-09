package ru.gb.trishkin.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class WebController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("message", "My message plus random UUID-> " + UUID.randomUUID());
        return "index";
    }
}
