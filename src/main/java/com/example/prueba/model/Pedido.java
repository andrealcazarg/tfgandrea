package com.example.prueba.model;

import javax.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    @Column
    private String fecha; //private Date fecha
    @Column
    private boolean confir;

    @ManyToOne()
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "FK_cliente"))
    Cliente cliente;

    public Pedido() {
    }

    public Pedido(int idPedido, String fecha, boolean confir, Cliente cliente) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.confir = confir;
        this.cliente = cliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isConfir() {
        return confir;
    }

    public void setConfir(boolean confir) {
        this.confir = confir;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fecha='" + fecha + '\'' +
                ", confir=" + confir +
                ", cliente=" + cliente +
                '}';
    }
}
