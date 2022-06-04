package com.example.prueba.repositories;

import com.example.prueba.model.Prueba;
import com.example.prueba.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email and u.password = :password")
    Usuario loginByPass(@Param("email") String email, @Param("password") String password);
}
