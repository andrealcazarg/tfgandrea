
package com.example.prueba.services.categoria;


import com.example.prueba.model.Categoria;

import java.util.List;

public interface ICategoriaService {
    Categoria add(Categoria u);
    List<Categoria> findAll();
    Categoria findById(Integer id);
    Categoria edit(Categoria u);
    void delete(Categoria u);

}
