package com.example.prueba.repositories;

import com.example.prueba.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    @Query("SELECT u from Cliente u where u.email = :email ")
    Cliente getCliente(String email);
}
