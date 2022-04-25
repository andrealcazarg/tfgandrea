package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {


}
