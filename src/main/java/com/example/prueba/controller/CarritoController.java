package com.example.prueba.controller;

import com.example.prueba.constantes.Constante;
import com.example.prueba.model.*;
import com.example.prueba.services.categoria.CategoriaService;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import com.example.prueba.services.pedido.PedidoService;
import com.example.prueba.services.producto.ProductoService;
import com.example.prueba.services.provincia.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

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
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping({"/tienda"})
    public String listarUsu(Model model) {
        model.addAttribute("listaProductosTienda", servicio.findAll());
        return "tienda";
    }
    @GetMapping("/categoria/{idCategoria}")
    public String listarCategoria(Model model, @PathVariable int idCategoria) {
        Categoria categoria = serviceCategoria.findById(idCategoria);

        model.addAttribute("listaProductos", servicio.selectProducto(idCategoria));
        model.addAttribute("listaCategoria", categoria);
        return "tiendaCategoria";
    }

    @GetMapping({"/productos/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable int idProducto) {

        LineaPedido lineaPedido = new LineaPedido();

        Producto producto = servicio.findById(idProducto);
        model.addAttribute("lineaPedido", lineaPedido);
        model.addAttribute("listaProductosTienda", producto);
        return "productos";
    }

    @PostMapping({"/carrito/{idProducto}"})
    public String carrito(@ModelAttribute("lineaPedido") LineaPedido lineaPedido, Pedido pedido, @PathVariable int idProducto) {
        pedido.setSesionID(servicePedido.obtenerID());

        Pedido pedido1 = servicePedido.selectPedido(pedido.getSesionID());

        if (pedido1 == null || pedido1.isConfir()) {
            servicePedido.add(pedido);
            lineaPedido.setPedido(pedido);
        } else {
            servicePedido.edit(pedido1);
            lineaPedido.setPedido(pedido1);
        }

        Producto producto = servicio.findById(idProducto);
        lineaPedido.setProducto(producto);

        LineaPedido linea = serviceLineaPedido.loginByProducto(lineaPedido.getProducto().getIdProducto());

        if (linea == null) {

            lineaPedido.setSubtotal(lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad());
            lineaPedido.setPtotal(lineaPedido.getProducto().getPeso() * lineaPedido.getCantidad());
            calcularEnvio(lineaPedido);

            serviceLineaPedido.add(lineaPedido);
            recorrerCarrito(lineaPedido);

        } else {
            linea.setCantidad(linea.getCantidad() + lineaPedido.getCantidad());

            linea.setSubtotal(linea.getSubtotal() + (lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad()));
            linea.setPtotal(linea.getPtotal() + (lineaPedido.getProducto().getPeso() * lineaPedido.getCantidad()));
            calcularEnvio(linea);

            serviceLineaPedido.edit(linea);
            recorrerCarrito(linea);
        }
        return "redirect:/tienda";
    }

    @GetMapping({"/carrito"})
    public String verCarrito(Model model, LineaPedido lineaPedido) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        lineaPedido.setPedido(pedido1);

        List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(pedido1.getIdPedido());

        model.addAttribute("lineasCarrito", lineaPedido1);
        model.addAttribute("listCategorias", serviceCategoria.findAll());
        if (lineaPedido1.size() ==0){
            pedido1.setpEnvio(0);
            pedido1.setTotalPedido(0);
            model.addAttribute("totalCarrito", lineaPedido.getPedido().getTotalPedido());
            model.addAttribute("precioEnvio",pedido1.getpEnvio());
        }
        model.addAttribute("totalCarrito", lineaPedido.getPedido().getTotalPedido());
        model.addAttribute("precioEnvio", pedido1.getpEnvio());
        return "carrito";
    }
  @GetMapping("/carrito/borrar/{id}")
    public String borrarProductoForm(@PathVariable int id){
        LineaPedido lineaPedido=serviceLineaPedido.findById(id);
        if (lineaPedido!= null) {
            serviceLineaPedido.delete(lineaPedido);
            recorrerCarrito(lineaPedido);
            return "redirect:/carrito";
            }else{
            return "redirect:/index";
        }
    }

    @GetMapping({"/cliente/facturacion"})
    public String clienteNewForm(Model model, LineaPedido lineaPedido) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        lineaPedido.setPedido(pedido1);

        List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(pedido1.getIdPedido());

        //CONFIRMAT
        model.addAttribute("clienteAceptar", new Cliente());
        model.addAttribute("provincias", provinciaService.findAll());
        model.addAttribute("lineasCarrito", lineaPedido1);
        model.addAttribute("totalCarrito", lineaPedido.getPedido().getTotalPedido());
        model.addAttribute("precioEnvio", pedido1.getpEnvio());

        return "clienteFacturacion";
    }

    @PostMapping({"/cliente/facturacion/submit"})
    public String nuevoclienteSubmit(@ModelAttribute("clienteAceptar")Cliente cliente) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        Cliente cliente1 =clienteService.getCliente(cliente.getEmail());

        //creo el cliente si no existe
        if(cliente1 ==null) {
            clienteService.add(cliente);
            pedido1.setCliente(cliente);
        }else{
            //añadir al pedido el cliente
            pedido1.setCliente(cliente1);
        }
        //confirmo el pedido y agrego la fecha actual
            pedido1.setFecha(String.valueOf(LocalDate.now()));
            pedido1.setConfir(true);
        //tambien borrar las lienas de pedido

        return "redirect:/pagarTarjeta";
    }
    @GetMapping({"/pagarTarjeta"})
    public String pagarTarjeta(Model model, Pedido pedido){
        //añadir paypal
        //model.addAttribute("listTarjeta",pedido);
        return "pagar";
    }

    public void recorrerCarrito(LineaPedido linea) {
        double totalCarrito = 0;
        List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(linea.getPedido().getIdPedido());

        for (LineaPedido lineaPedido : lineaPedido1) {
            totalCarrito = totalCarrito + lineaPedido.getSubtotal();
        }

        linea.getPedido().setTotalPedido(totalCarrito + linea.getPedido().getpEnvio());
    }
    public void calcularEnvio(@ModelAttribute("lineaPedido") LineaPedido linea) {

        if (linea.getPtotal() < 3) {
            linea.getPedido().setpEnvio(8);
        } else if (linea.getPtotal() >= 3 && linea.getPtotal() < 6) {
            linea.getPedido().setpEnvio(10);
        } else if (linea.getPtotal() >= 6 && linea.getPtotal() < 10) {
            linea.getPedido().setpEnvio(12);
        } else {
            linea.getPedido().setpEnvio(18);
        }
    }

}
