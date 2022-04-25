package com.example.prueba.services.cliente;


import com.example.prueba.model.Cliente;

import java.util.List;

public interface IClienteService {
    Cliente add(Cliente u);
    List<Cliente> findAll();
    Cliente findById(Integer id);
    Cliente edit(Cliente u);
    void delete(Cliente u);

}
