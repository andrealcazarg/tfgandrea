package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Pedido;
import com.example.prueba.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    @Query("SELECT u FROM Producto u where  u.categoria.idCategoria = :idCategoria")
    List<Producto> selectProducto(@Param("idCategoria") Integer idCategoria);

   @Query("SELECT u FROM Producto u where u.disponible = true")
   List<Producto> selectAllProducto();
}
