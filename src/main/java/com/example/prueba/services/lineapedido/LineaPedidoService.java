package com.example.prueba.services.lineapedido;


import com.example.prueba.model.LineaPedido;
import com.example.prueba.repositories.LineaPedidoRepository;
import com.example.prueba.repositories.PedidoRepository;
import com.example.prueba.services.pedido.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.Kernel;
import java.util.List;

@Service
public class LineaPedidoService implements ILineaPedidoService {

    @Autowired
    private LineaPedidoRepository repositorio;

    @Override
    public LineaPedido add(LineaPedido u) {
        return repositorio.save(u);
    }

    @Override
    public List<LineaPedido> findAll() {
        return repositorio.findAll();
    }

    @Override
    public LineaPedido findById(Integer id) {
        return repositorio.findById(id).orElse(null);
    }


    @Override
    public LineaPedido edit(LineaPedido u) {
        return repositorio.save(u);
    }

    @Override
    public void delete(LineaPedido u) {
        repositorio.delete(u);
    }

    @Override
    public List<LineaPedido> selectLineas(Integer idPedido) {
        return repositorio.selectLineas(idPedido);
    }


    public LineaPedido loginByProducto(Integer idProducto) {
        return repositorio.loginByProducto(idProducto);
    }

    @Override
    public LineaPedido selectLineasPendiente(Integer idPedido) {
        return repositorio.selectLineasPendiente(idPedido);
    }
}
