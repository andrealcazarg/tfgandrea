package com.example.prueba.controller;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Provincia;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.provincia.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService service;
    @Autowired
    private ProvinciaService servicio;

    @GetMapping({"cliente/list"})
    public String listar(Model model) {
        model.addAttribute("listaClientes", service.findAll());
        return "listCliente";
    }

    @GetMapping("/cliente/new")
    public String clienteNewForm(Model model) {
        model.addAttribute("clienteForm", new Cliente());
        model.addAttribute("provincias", servicio.findAll());
        return "formCliente";
    }

    @PostMapping("/cliente/new/submit")
    public String nuevoclienteSubmit(@ModelAttribute("clienteForm") Cliente cliente) { // recibimos el command objet a traves de @ModelAttribute que lo coge desde el form y lo inyecta en el atributo empleadoForm
        service.add(cliente);        //@ModelAttribute realizar un binding de los datos de un formulario de Spring con el servicio.
        return "redirect:/cliente/list";
    }

    @GetMapping("/cliente/edit/{id}")                                   //
    public String editarclienteForm(@PathVariable Integer id, Model model,Cliente cliente) {  // recibimos id desde el path
       cliente = service.findById(id);                        // pasamos el id al servicio
        if (cliente != null) {
            model.addAttribute("clienteForm", cliente);//añadimos atributo e instancia del commandobject
            model.addAttribute("provincias", servicio.findAll());
            return "formCliente";
        } else {
            return "redirect:/cliente/new";
        }
    }

    @PostMapping("/cliente/edit/submit")
    public String editarclienteSubmit(@ModelAttribute("clienteForm") Cliente cliente) { // recibimos el command objet a traves de @ModelAttribute que lo coge desde el form y lo inyecta en el atributo empleadoForm
        service.edit(cliente);       //@ModelAttribute realiza un binding de los datos de un formulario de Spring con el servicio.
        return "redirect:/cliente/list";
    }

    @GetMapping("/cliente/borrar/{id}")                                   //
    public String borrarclienteForm(@PathVariable Integer id) {  // recibimos id desde el path
        Cliente cliente = service.findById(id);                        // pasamos el id al servicio
        if (cliente != null) {
            service.delete(cliente);
            return "redirect:/cliente/list";
        } else {
            return "redirect:/cliente/new";
        }
    }

}
