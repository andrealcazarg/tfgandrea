package com.example.prueba.controller;

import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import com.example.prueba.model.Producto;
import com.example.prueba.services.categoria.CategoriaService;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import com.example.prueba.services.pedido.PedidoService;
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
    private PedidoService servicePedido;

    @Autowired
    private LineaPedidoService serviceLineaPedido;
    @GetMapping({"/tienda"})
    public String listarUsu(Model model){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "tienda";
    }

    @GetMapping({"/productos/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable int idProducto){
        Producto producto=servicio.findById(idProducto);
        model.addAttribute("listaProductosTienda", producto) ;// inyecta el servicio gracias al @Autowired anterior
        //Realizar la Linea de Pedido
        LineaPedido linea = null;
        Pedido pedido = null;
        if (pedido == null) {
            pedido = new Pedido(1,"2022-02-21",false,pedido.getCliente());
            servicePedido.add(pedido);
            linea = new LineaPedido(1,linea.getCantidad(),linea.getSubtotal(),linea.getPtotal(),linea.getPenvio(),linea.getTotal(),linea.getPedido(),linea.getProducto());
            serviceLineaPedido.add(linea);

        }
        return "productos";
    }
    @GetMapping({"/carrito/{idProducto}"})
    public String carrito(Model model, @PathVariable int idProducto, LineaPedido lineaPedido){
        //cargar La linea de Pedido correspondiente al
        Producto producto=servicio.findById(idProducto);
        model.addAttribute("listaProductosCarrito", producto) ;// inyecta el servicio gracias al @Autowired anterior
       /* producto.getPrecio();
        producto.getPeso();*/
        return "carrito";
    }
}
