package com.example.prueba.controller;

import com.example.prueba.model.Producto;
import com.example.prueba.services.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarritoController {


    @Autowired
    private ProductoService servicio;


    @GetMapping({"/tienda/list"})
    public String listarUsu(Model model){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "tienda";
    }

    @GetMapping({"/tienda/list/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable Producto producto){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "productos";
    }
}
