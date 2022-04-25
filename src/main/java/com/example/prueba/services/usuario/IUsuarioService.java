package com.example.prueba.services.usuario;


import com.example.prueba.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario add(Usuario u);
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Usuario edit(Usuario u);
    void delete(Usuario u);
    Usuario loginByPass(String email,String pass);
}
