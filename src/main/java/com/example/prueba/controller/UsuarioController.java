package com.example.prueba.controller;


import com.example.prueba.constantes.Constante;
import com.example.prueba.model.Usuario;
import com.example.prueba.services.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST CREADA. PARA COMPROBAR ABRIR POSTMAN CON LOCALHOST:8080/
 */
//@RestController
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
    public String usuarioCerrarSesion(){
        Constante.login = null;
        return "redirect:/admin";
    }


    /* @PostMapping("/addUsuario")
    public void addUser(@RequestBody Usuario usuario) {
        //Este metodo guardará al usuario enviado
        servicio.add(usuario);
    }
    @GetMapping("prueba/{id}")
    public Usuario getUsuario(@PathVariable Integer id){
        return servicio.findById(id);
    }

    @GetMapping("/listarUsuario")
    public List<Usuario> findAll(){
        //retornará todos los usuarios
        return servicio.findAll();
    }
    @DeleteMapping("borrar/{id}")
    public void deteteUser(@PathVariable Integer id) {

        Usuario usuario = servicio.findById(id);
        servicio.delete(usuario);
    }
    @PutMapping("/modiUsuario/{id}")
    public void updateUser(@RequestBody Usuario usuarioedit, @PathVariable int id) {
        Usuario prueba = servicio.findById(id);
       // prueba.setNombre(pruebaedit.getNombre());
        servicio.edit(prueba);
    }*/
}
