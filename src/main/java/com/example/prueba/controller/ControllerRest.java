package com.example.prueba.controller;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import com.example.prueba.model.Prueba;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import com.example.prueba.services.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerRest {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private LineaPedidoService lineaPedidoService;

    @GetMapping("/listCliente")
    public List<Cliente> findAllCliente(){

        return clienteService.findAll();
    }
    @GetMapping("/cliente/{id}")
    public Cliente getCliente(@PathVariable Integer id){
        return clienteService.findById(id);
    }

    @GetMapping("/listPedido")
    public List<Pedido> findAllPedido(){ //enviado ==true
        return pedidoService.findAllPedido();
    }
    @GetMapping("/pedidoConfir") //enviado ==false
    public List<Pedido> getConfir(){
        return pedidoService.findConfir();
    }
  /*  @GetMapping("/pedido/{id}")
    public Pedido getPedido(@PathVariable Integer id){
        return pedidoService.findById(id);
    }*/
    @GetMapping("/confirmar/{id}") //inf cliente,linea pedido x id pedido
    public List<LineaPedido> selectLineasPendiente(@PathVariable Integer id){
        return lineaPedidoService.selectLineasPendiente(id);
    }
    @PutMapping("/modiPedido/{id}")
    public void updatePedido(@RequestBody Pedido pedido1, @PathVariable int id) {
        Pedido pedido = pedidoService.findById(id);
        //pedido.set(pedido1.getNombre());

        pedido.setEnviado(pedido1.isEnviado());
        pedidoService.edit(pedido);


    }
}
