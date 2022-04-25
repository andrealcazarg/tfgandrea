package com.example.prueba.services.categoria;


import com.example.prueba.model.Categoria;
import com.example.prueba.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository repositorio;

    @Override
    public Categoria add(Categoria u) {
        return repositorio.save(u);
    }

    @Override
    public List<Categoria> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Categoria edit(Categoria u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Categoria u) {
        repositorio.delete(u);
    }

}
