package com.example.prueba.services.producto;


import com.example.prueba.model.Producto;
import com.example.prueba.repositories.ProductoRepository;
import com.example.prueba.repositories.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repositorio;

    @Override
    public Producto add(Producto u) {
        return repositorio.save(u);
    }

    @Override
    public List<Producto> findAll() {
        return repositorio.findAll();
    }

    @Override
    public Producto findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public Producto edit(Producto u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(Producto u) {
        repositorio.delete(u);
    }

    @Override
    public List<Producto> selectProducto(Integer idCategoria) {
        return repositorio.selectProducto(idCategoria);
    }

}
