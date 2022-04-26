package com.example.prueba.controller;

import com.example.prueba.model.Pedido;
import com.example.prueba.model.Producto;
import com.example.prueba.services.categoria.CategoriaService;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.pedido.PedidoService;
import com.example.prueba.services.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService servicio;
    @Autowired
    private CategoriaService service;

    @GetMapping("/producto/new")
    public String nuevoProductoForm(Model model) {
        model.addAttribute("productoForm", new Producto());
        model.addAttribute("categorias", service.findAll());
        return "formProducto";
    }
    @GetMapping({"/producto/list"})
    public String listarUsu(Model model){
        model.addAttribute("listaProductos", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "listProducto";
    }
  /*  @GetMapping("/producto/new/{idProducto}")
    public String nuevoProductoForm2(@PathVariable int idProducto, Model model) {
        Producto producto = servicio.findById(idProducto);//add el producto con x id
        LineaPedido linea = null;
        Pedido pedido = null;
        if (pedido == null) {
            pedido = new Pedido(1,"2022-02-21",false);
            servicePedido.add(pedido);
            linea = new LineaPedido(1,1,pedido,producto);
            lineaPedidoService.add(linea);

        }
        return "redirect:/producto/list";
    }*/
    @PostMapping("/producto/new/submit")
    public String nuevoProductoSubmit2(@ModelAttribute("productoForm") Producto producto){ // recibimos el command objet a traves de @ModelAttribute que lo coge desde el form y lo inyecta en el atributo empleadoForm
        servicio.add(producto);        //@ModelAttribute realizar un binding de los datos de un formulario de Spring con el servicio.
        return "redirect:/producto/list";
    }


    @GetMapping("/producto/edit/{id}")                                   //
    public String editarProductoForm(@PathVariable int id, Model model){  // recibimos id desde el path
        Producto producto=servicio.findById(id);                        // pasamos el id al servicio
        if (producto!= null) {
            model.addAttribute("productoForm", producto); //añadimos atributo e instancia del commandobject
            model.addAttribute("categorias", service.findAll());
            return "formProducto";
        }else{
            return "redirect:/producto/new";
        }
    }

    @PostMapping("/producto/edit/submit")
    public String editarProductoSubmit(@ModelAttribute("productoForm") Producto producto ){ // recibimos el command objet a traves de @ModelAttribute que lo coge desde el form y lo inyecta en el atributo empleadoForm
        servicio.edit(producto);       //@ModelAttribute realiza un binding de los datos de un formulario de Spring con el servicio.
        return "redirect:/producto/list";
    }

    @GetMapping("/producto/borrar/{id}")                                   //
    public String borrarProductoForm(@PathVariable int id){  // recibimos id desde el path
        Producto producto=servicio.findById(id);                        // pasamos el id al servicio
        if (producto!= null) {
            servicio.delete(producto);
            return "redirect:/producto/list";
        }else{
            return "redirect:/producto/new";
        }
    }
}
