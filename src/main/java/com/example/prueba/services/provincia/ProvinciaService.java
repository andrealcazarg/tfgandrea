package com.example.prueba.services.provincia;


import com.example.prueba.model.Cliente;
import com.example.prueba.model.Provincia;
import com.example.prueba.repositories.ClienteRepository;
import com.example.prueba.repositories.ProvinciaRepository;
import com.example.prueba.services.cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService implements IProvinciaService {

    @Autowired
    private ProvinciaRepository repositorio;

    @Override
    public Provincia add(Provincia u) {
        return repositorio.save(u);
    }

    @Override
    public List<Provincia> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Provincia findById(String id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Provincia edit(Provincia u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Provincia u) {
        repositorio.delete(u);
    }

}
