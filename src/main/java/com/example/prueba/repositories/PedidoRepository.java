package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query("SELECT u FROM Pedido u where  u.sesionID = :sessionID and u.confir= false")
    Pedido selectPedido( @Param("sessionID") String sessionID);
    @Query(value = "SELECT SESSION_ID FROM SPRING_SESSION", nativeQuery = true)
    String obtenerID();
    @Query("select u from Pedido u where u.enviado = true ")
    List<Pedido> findAllPedido();
    @Query("select u from Pedido u where u.enviado = false")
    List<Pedido> findConfir();
}
