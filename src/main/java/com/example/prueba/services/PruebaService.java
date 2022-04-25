package com.example.prueba.services;

import com.example.prueba.model.Prueba;
import com.example.prueba.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService implements IPruebaService{

    @Autowired
    private PruebaRepository repositorio;

    @Override
    public Prueba add(Prueba u) {
        return repositorio.save(u);
    }

    @Override
    public List<Prueba> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Prueba findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Prueba edit(Prueba u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Prueba u) {
        repositorio.delete(u);
    }
}
