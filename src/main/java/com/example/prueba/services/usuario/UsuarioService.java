package com.example.prueba.services.usuario;


import com.example.prueba.model.Usuario;
import com.example.prueba.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    @Override
    public Usuario add(Usuario u) {
        return repositorio.save(u);
    }

    @Override
    public List<Usuario> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Usuario edit(Usuario u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Usuario u) {
        repositorio.delete(u);
    }

    @Override
    public Usuario loginByPass(String email, String password) {
        return repositorio.loginByPass(email,password);
    }
}
