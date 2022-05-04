package com.example.prueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String welcome(Model model) {
        return "index";
    }
    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        return "nosotros";
    }




}
