package com.example.prueba.repositories;

import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido,Integer> {

    @Query("SELECT u FROM LineaPedido u where u.pedido.idPedido = :idPedido and u.pedido.confir= false")
    List<LineaPedido> selectLineas(@Param("idPedido") Integer idPedido);

    @Query("SELECT u FROM LineaPedido u WHERE u.producto.IdProducto = :idProducto and u.pedido.confir= false")
    LineaPedido loginByProducto(@Param("idProducto") Integer idProducto);


}
