package com.example.prueba.services.lineapedido;



import com.example.prueba.model.LineaPedido;

import java.util.List;

public interface ILineaPedidoService {
    LineaPedido add(LineaPedido u);
    List<LineaPedido> findAll();
    LineaPedido findById(Integer id);
    LineaPedido edit(LineaPedido u);
    void delete(LineaPedido u);
    List<LineaPedido> selectLineas(Integer idPedido);
    LineaPedido loginByProducto(Integer idProducto);

}