package com.example.prueba.controller;

import com.example.prueba.model.Prueba;
import com.example.prueba.model.Usuario;
import com.example.prueba.services.PruebaService;
import com.example.prueba.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST CREADA. PARA COMPROBAR ABRIR POSTMAN CON LOCALHOST:8080/
 */

@RestController
public class PruebaController {

    @Autowired
    private PruebaService servicio;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/addPrueba")
    public void addUser(@RequestBody Prueba prueba) {
        //Este metodo guardará al usuario enviado
        servicio.add(prueba);
    }
    @GetMapping("prueba/{id}")
    public Prueba getPrueba(@PathVariable Integer id){
        return servicio.findById(id);
    }

    @GetMapping("/listarPrueba")
    public List<Prueba> findAll(){
        //retornará todos los usuarios
        return servicio.findAll();
    }
    @DeleteMapping("borrar/{id}")
    public void deteteUser(@PathVariable Integer id) {

        Prueba prueba = servicio.findById(id);
        servicio.delete(prueba);
    }
    @PutMapping("/modiPrueba/{id}")
    public void updateUser(@RequestBody Prueba pruebaedit, @PathVariable int id) {
        Prueba prueba = servicio.findById(id);
        prueba.setNombre(pruebaedit.getNombre());
        prueba.setConfir(pruebaedit.isConfir());
        servicio.edit(prueba);


    }
    @GetMapping("/listarUsu/{id}")
    public Usuario getUsu(@PathVariable Integer id){
        return usuarioService.findById(id);
    }

}
