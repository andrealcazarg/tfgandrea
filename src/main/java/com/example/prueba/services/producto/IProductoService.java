package com.example.prueba.services.producto;


import com.example.prueba.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto add(Producto u);
    List<Producto> findAll();
    Producto findById(Integer id);
    Producto edit(Producto u);
    void delete(Producto u);
    List<Producto> selectProducto(Integer idCategoria);

}
