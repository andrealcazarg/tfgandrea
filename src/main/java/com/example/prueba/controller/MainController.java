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

/*    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        String session_id = service.obtenerID();
        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);

        return "index";
    }*/



}
