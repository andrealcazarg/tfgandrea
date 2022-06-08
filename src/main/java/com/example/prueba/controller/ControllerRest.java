package com.example.prueba.controller;

import com.example.prueba.model.*;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import com.example.prueba.services.pedido.PedidoService;
import com.example.prueba.services.usuario.UsuarioService;
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
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listCliente")
    public List<Cliente> findAllCliente(){
        return clienteService.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getCliente(@PathVariable Integer id){
        return clienteService.findById(id);
    }
    /**
     *
     * @return todos los pedidos where enviado = true
     */
    @GetMapping("/listPedido")
    public List<Pedido> findAllPedido(){
        return pedidoService.findAllPedido();
    }
    /**
     *
     * @return todos los pedidos where enviado = false
     */
    @GetMapping("/pedidoConfir")
    public List<Pedido> getConfir(){
        return pedidoService.findConfir();
    }

    @GetMapping("/infCliente/{id}") //inf cliente,linea pedido x id pedido
    public List<LineaPedido> selectLineasPendiente(@PathVariable Integer id){
        return lineaPedidoService.selectLineasPendiente(id);
    }

    @PutMapping("/modiPedido/{id}")
    public void updatePedido(@RequestBody Pedido pedido) {
        pedido.setEnviado(true);
        pedidoService.edit(pedido);
    }

    @GetMapping("/login/{email}/{password}")
    public Usuario loginByPass(@PathVariable String email, @PathVariable String password){
        return usuarioService.loginByPass(email,password);
    }
}
