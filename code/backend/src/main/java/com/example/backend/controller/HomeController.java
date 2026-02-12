package com.example.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // ... inside UiController class ...

    @GetMapping("/")
    public String root() {
        return "redirect:/ui/orders";
    }
}
