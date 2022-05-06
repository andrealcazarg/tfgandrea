package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query("SELECT u FROM LineaPedido u where u.producto.IdProducto = :idProducto")
    LineaPedido selectLineas(@Param("idProducto") Integer idProducto);
}
