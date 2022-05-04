package com.example.prueba.controller;

import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Producto;
import com.example.prueba.services.categoria.CategoriaService;
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
    @Autowired
    private CategoriaService service;

    @GetMapping({"/tienda"})
    public String listarUsu(Model model){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "tienda";
    }

    @GetMapping({"/productos/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable int idProducto){
        Producto producto=servicio.findById(idProducto);
        model.addAttribute("listaProductosTienda", producto) ;// inyecta el servicio gracias al @Autowired anterior
        model.addAttribute("categorias", service.findAll());
        return "productos";
    }
    @GetMapping({"/carrito{idProducto}"})
    public String carrito(Model model, @PathVariable int idProducto, LineaPedido lineaPedido){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "tienda";
    }
}
