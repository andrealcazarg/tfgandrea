package com.example.prueba.controller;


import com.example.prueba.constantes.Constante;
import com.example.prueba.model.Usuario;
import com.example.prueba.services.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "admin";
    }

    @PostMapping("/admin/submit")
    public String login(@ModelAttribute("loginForm") Usuario usuario) {
        Usuario usuarioTemp = servicio.loginByPass(usuario.getEmail(), usuario.getPassword());
        if (usuarioTemp != null) {
            Constante.login = usuarioTemp;
            return "redirect:/cliente/list"; //INICIA SESION
        } else {
            return "admin"; //DATOS INCORRECTOS
        }
    }

    @GetMapping("/admin/cerrar")
    public String usuarioCerrarSesion() {
        Constante.login = null;
        return "redirect:/admin";
    }
}
