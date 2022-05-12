package com.example.prueba.controller;

import com.example.prueba.constantes.Constante;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

     @Autowired
     private LineaPedidoService service;

    @GetMapping("/")
    public String welcome(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
        return "index";
    }
    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        return "nosotros";
    }

    @GetMapping("/apadrina")
    public String apadrina(Model model) {
        return "apadrina";
    }

}
