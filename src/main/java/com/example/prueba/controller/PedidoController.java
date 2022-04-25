package com.example.prueba.controller;

import com.example.prueba.model.Pedido;

import com.example.prueba.services.cliente.ClienteService;

import com.example.prueba.services.pedido.PedidoService;
import com.example.prueba.services.provincia.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService servicio;
    @Autowired
    private ClienteService service;

    @GetMapping({"pedido/list"})
    public String listar(Model model) {
        model.addAttribute("listaPedidos", servicio.findAll());
        return "listPedido";
    }

    @GetMapping("/pedido/new")
    public String pedidoNewForm(Model model) {
        model.addAttribute("pedidoForm", new Pedido());
        model.addAttribute("clientes", service.findAll());
        return "formPedido";
    }

    @PostMapping("/pedido/new/submit")
    public String nuevopedidoSubmit(@ModelAttribute("pedidoForm") Pedido pedido) { // recibimos el command objet a traves de @ModelAttribute que lo coge desde el form y lo inyecta en el atributo empleadoForm
        servicio.add(pedido);        //@ModelAttribute realizar un binding de los datos de un formulario de Spring con el servicio.
        return "redirect:/pedido/list";
    }

    @GetMapping("/pedido/edit/{id}")                                   //
    public String editarpedidoForm(@PathVariable Integer id, Model model, Pedido pedido) {  // recibimos id desde el path
       pedido = servicio.findById(id);                        // pasamos el id al servicio
        if (pedido != null) {
            model.addAttribute("pedidoForm", pedido);//añadimos atributo e instancia del commandobject
            model.addAttribute("clientes", service.findAll());
            return "formPedido";
        } else {
            return "redirect:/pedido/new";
        }
    }

    @PostMapping("/pedido/edit/submit")
    public String editarpedidoSubmit(@ModelAttribute("pedidoForm") Pedido pedido) { // recibimos el command objet a traves de @ModelAttribute que lo coge desde el form y lo inyecta en el atributo empleadoForm
        servicio.edit(pedido);       //@ModelAttribute realiza un binding de los datos de un formulario de Spring con el servicio.
        return "redirect:/pedido/list";
    }

    @GetMapping("/pedido/borrar/{id}")                                   //
    public String borrarpedidoForm(@PathVariable Integer id) {  // recibimos id desde el path
        Pedido pedido = servicio.findById(id);                        // pasamos el id al servicio
        if (pedido != null) {
            servicio.delete(pedido);
            return "redirect:/pedido/list";
        } else {
            return "redirect:/pedido/new";
        }
    }

}
