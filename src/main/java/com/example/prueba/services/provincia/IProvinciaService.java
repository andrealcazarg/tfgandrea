package com.example.prueba.services.provincia;

import com.example.prueba.model.Provincia;

import java.util.List;

public interface IProvinciaService {
    Provincia add(Provincia u);
    List<Provincia> findAll();
    Provincia findById(String id);
    Provincia edit(Provincia u);
    void delete(Provincia u);

}
