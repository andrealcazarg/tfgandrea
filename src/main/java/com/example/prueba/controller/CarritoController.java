package com.example.prueba.controller;

import com.example.prueba.constantes.Constante;
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

import java.time.LocalDate;

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
        return "productos";
    }
    @GetMapping({"/carrito/{idProducto}"})
    public String carrito(Model model, @PathVariable int idProducto, LineaPedido lineaPedido , Pedido pedido){
        //cargar La linea de Pedido correspondiente al
        String session_id = servicePedido.obtenerID();
        Constante.SESSION_ID = session_id;
        pedido = new Pedido(String.valueOf(LocalDate.now()),false,Constante.SESSION_ID);
        servicePedido.add(pedido);
        if (pedido ==null ){


        }else if(pedido != null && !pedido.isConfir()) {
            servicePedido.edit(pedido);
        }

        Producto producto=servicio.findById(idProducto);
        lineaPedido.setProducto(producto);
        lineaPedido.setPedido(pedido);

        LineaPedido linea =null;
        linea =serviceLineaPedido.loginByProducto(lineaPedido.getProducto().getIdProducto(),pedido.getIdPedido());
        serviceLineaPedido.add(lineaPedido);


        model.addAttribute("listaProductosCarrito", producto) ;// inyecta el servicio gracias al @Autowired anterior
        return "redirect:/tienda";
    }
    @GetMapping({"/carrito"})
    public String verCarrito(Model model,Pedido pedido){
        model.addAttribute("listaProductosCarrito",serviceLineaPedido.selectLineas(pedido.getIdPedido(), Constante.SESSION_ID));
        return "carrito";
    }
}
