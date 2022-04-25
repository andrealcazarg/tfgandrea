package com.example.prueba.services.cliente;


import com.example.prueba.model.Cliente;
import com.example.prueba.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository repositorio;

    @Override
    public Cliente add(Cliente u) {
        return repositorio.save(u);
    }

    @Override
    public List<Cliente> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Cliente findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Cliente edit(Cliente u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Cliente u) {
        repositorio.delete(u);
    }

}
