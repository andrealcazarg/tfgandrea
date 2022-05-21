package com.example.prueba.services.pedido;


import com.example.prueba.model.Pedido;
import com.example.prueba.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository repositorio;

    @Override
    public Pedido add(Pedido u) {
        return repositorio.save(u);
    }

    @Override
    public List<Pedido> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Pedido findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Pedido edit(Pedido u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Pedido u) {
        repositorio.delete(u);
    }

    @Override
    public String obtenerID() {
        return  repositorio.obtenerID();
    }

    @Override
    public Pedido selectPedido(String sessionID) {
        return repositorio.selectPedido(sessionID);
    }

    @Override
    public Pedido findConfir() {
        return repositorio.findConfir();
    }


}
