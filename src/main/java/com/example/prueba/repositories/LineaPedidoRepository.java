package com.example.prueba.repositories;

import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido,Integer> {

    @Query("SELECT u FROM LineaPedido u where u.pedido.idPedido = :idPedido ")
    List<LineaPedido> selectLineas(@Param("idPedido") Integer idPedido);

    @Query("SELECT u.producto, u.pedido FROM LineaPedido u WHERE u.producto.IdProducto = :idProducto and u.pedido.idPedido = :idPedido")
    LineaPedido loginByProducto(@Param("idProducto") Integer idProducto, @Param("idPedido") Integer idPedido);


}
