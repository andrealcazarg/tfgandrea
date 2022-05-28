package com.example.prueba.services.pedido;


import com.example.prueba.model.Pedido;

import java.util.List;

public interface IPedidoService {
    Pedido add(Pedido u);
    List<Pedido> findAll();
    Pedido findById(Integer id);
    Pedido edit(Pedido u);
    void delete(Pedido u);
    String obtenerID();
    Pedido selectPedido(String sessionID);
    List<Pedido> findAllPedido();
    List<Pedido> findConfir();
}
