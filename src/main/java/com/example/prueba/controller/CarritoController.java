package com.example.prueba.controller;

import com.example.prueba.constantes.Constante;
import com.example.prueba.model.Categoria;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class CarritoController {


    @Autowired
    private ProductoService servicio;
    @Autowired
    private PedidoService servicePedido;

    @Autowired
    private CategoriaService serviceCategoria;
    @Autowired
    private LineaPedidoService serviceLineaPedido;

    @GetMapping({"/tienda"})
    public String listarUsu(Model model){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "tienda";
    }
    @GetMapping("/categoria/{idCategoria}")
    public String listarCategoria(Model model, @PathVariable int idCategoria){
        Categoria categoria=serviceCategoria.findById(idCategoria);

        model.addAttribute("listaProductos",servicio.selectProducto(idCategoria));
        model.addAttribute("listaCategoria",categoria);
        return "tiendaCategoria";
    }

    @GetMapping({"/productos/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable int idProducto){

        LineaPedido lineaPedido= new LineaPedido();

        Producto producto=servicio.findById(idProducto);
        model.addAttribute("lineaPedido", lineaPedido);
        model.addAttribute("listaProductosTienda", producto) ;// inyecta el servicio gracias al @Autowired anterior

        return "productos";
    }
    @PostMapping({"/carrito/{idProducto}"})
    public String carrito(@ModelAttribute("lineaPedido") LineaPedido lineaPedido , Pedido pedido,  @PathVariable int idProducto){
        //cargar La linea de Pedido correspondiente al
        String session_id = servicePedido.obtenerID();
        Constante.SESSION_ID = session_id;
        pedido.setSesionID(session_id);
       Pedido pedido1 = servicePedido.selectPedido( Constante.SESSION_ID);

       if (pedido1 ==null){
           servicePedido.add(pedido);
        }else {
            servicePedido.edit(pedido1);
        }

        Producto producto=servicio.findById(idProducto);

        lineaPedido.setProducto(producto);
        lineaPedido.setPedido(pedido1);

        LineaPedido linea =serviceLineaPedido.loginByProducto(lineaPedido.getProducto().getIdProducto(),pedido.getIdPedido());

        if (linea ==null ){

            lineaPedido.setSubtotal(producto.getPrecio() * lineaPedido.getCantidad());
            serviceLineaPedido.add(lineaPedido);

        }else {
            linea.setCantidad(linea.getCantidad() + lineaPedido.getCantidad());
             linea.setSubtotal(linea.getSubtotal() + (lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad()));
            serviceLineaPedido.edit(linea);
        }

       // model.addAttribute("listaProductosCarrito", producto) ;// inyecta el servicio gracias al @Autowired anterior
        return "redirect:/tienda";
    }
    @GetMapping({"/carrito"})
    public String verCarrito(Model model){
       /* String session_id = servicePedido.obtenerID();
        Constante.SESSION_ID = session_id;
        pedido.setSesionID(session_id);*/
        model.addAttribute("lineasCarrito",serviceLineaPedido.findAll());
        model.addAttribute("listCategorias",serviceCategoria.findAll());
        return "carrito";
    }
}
