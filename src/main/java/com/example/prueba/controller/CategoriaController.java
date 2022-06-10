package com.example.prueba.controller;

import com.example.prueba.model.Categoria;
import com.example.prueba.services.categoria.CategoriaService;
import com.example.prueba.services.provincia.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping({"categoria/list"})
    public String listar(Model model) {
        model.addAttribute("listaCategorias", service.findAll());
        return "listCategoria";
    }

    @GetMapping("/categoria/new")
    public String CategoriaNewForm(Model model) {
        model.addAttribute("categoriaForm", new Categoria());
        model.addAttribute("provincias", service.findAll());
        return "formCategoria";
    }

    @PostMapping("/categoria/new/submit")
    public String nuevoCategoriaSubmit(@ModelAttribute("categoriaForm") Categoria categoria) {
        service.add(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/categoria/edit/{id}")
    public String editarCategoriaForm(@PathVariable Integer id, Model model, Categoria categoria) {
        categoria = service.findById(id);                        // pasamos el id al servicio
        if (categoria != null) {
            model.addAttribute("categoriaForm", categoria);
            model.addAttribute("provincias", service.findAll());
            return "formCategoria";
        } else {
            return "redirect:/categoria/new";
        }
    }

    @PostMapping("/categoria/edit/submit")
    public String editarCategoriaSubmit(@ModelAttribute("categoriaForm") Categoria categoria) {
        service.edit(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/categoria/borrar/{id}")
    public String borrarCategoriaForm(@PathVariable Integer id) {
        Categoria categoria = service.findById(id);
        if (categoria != null) {
            service.delete(categoria);
            return "redirect:/categoria/list";
        } else {
            return "redirect:/categoria/new";
        }
    }

}
