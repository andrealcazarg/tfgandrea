package com.example.prueba.services;

import com.example.prueba.model.Prueba;

import java.util.List;

public interface IPruebaService {
    Prueba add(Prueba u);
    List<Prueba> findAll();
    Prueba findById(Integer id);
    Prueba edit(Prueba u);
    void delete(Prueba u);
}
