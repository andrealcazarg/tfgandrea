package com.example.prueba.controller;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Pedido;
import com.example.prueba.model.Prueba;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerRest {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listCliente")
    public List<Cliente> findAllCliente(){

        return clienteService.findAll();
    }
    @GetMapping("cliente/{id}")
    public Cliente getCliente(@PathVariable Integer id){
        return clienteService.findById(id);
    }
    @GetMapping("/listPedido")
    public List<Pedido> findAllPedido(){

        return pedidoService.findAll();
    }
    @GetMapping("pedidoConfir")
    public Pedido getConfir(){
        return pedidoService.findConfir();
    }
    @GetMapping("pedido/{id}")
    public Pedido getPedido(@PathVariable Integer id){
        return pedidoService.findById(id);
    }
    //confirmar Pedido
}
