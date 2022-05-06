package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
/*
    @Query(value = "SELECT SESSION_ID FROM spring_session", nativeQuery = true)
    Object obtenerID();*/
}
