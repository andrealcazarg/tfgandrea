package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {


}
