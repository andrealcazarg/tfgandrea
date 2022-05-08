package com.example.prueba.services.lineapedido;



import com.example.prueba.model.LineaPedido;

import java.util.List;

public interface ILineaPedidoService {
    LineaPedido add(LineaPedido u);
    List<LineaPedido> findAll();
    LineaPedido findById(Integer id);
    LineaPedido edit(LineaPedido u);
    void delete(LineaPedido u);
    LineaPedido selectLineas(Integer idPedido, String sessionID);
    LineaPedido loginByProducto(Integer idProducto,Integer idPedido);

}
