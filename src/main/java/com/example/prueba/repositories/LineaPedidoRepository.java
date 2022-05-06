package com.example.prueba.repositories;

import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido,Integer> {


    @Query(value = "SELECT SESSION_ID FROM SPRING_SESSION", nativeQuery = true)
    Object obtenerID();

    @Query("SELECT u.producto FROM LineaPedido u WHERE u.producto.IdProducto = :idProducto")
    LineaPedido loginByProducto(@Param("idProducto") Integer idProducto);


}
