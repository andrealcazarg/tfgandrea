package com.example.prueba.model;

import javax.persistence.*;

@Entity
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLineaPedido;
    @Column
    private int cantidad;
    @Column(precision = 5, scale = 2)
    private double subtotal; //decimal (5,2)
     @Column(precision = 5, scale = 2)
    private double ptotal; //peso X cantidad

    @Column(precision = 5, scale = 2)
    private double penvio; //el precio del envio

     @Column(precision = 5, scale = 2)
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_Pedido", foreignKey = @ForeignKey(name = "FK_pedido"))
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_Producto", foreignKey = @ForeignKey(name = "FK_producto"))
    private Producto producto;

    public LineaPedido() {
    }

    public LineaPedido(int cantidad, double subtotal, double ptotal, double penvio, double total, Pedido pedido, Producto producto) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.ptotal = ptotal;
        this.penvio = penvio;
        this.total = total;
        this.pedido = pedido;
        this.producto = producto;
    }

    public LineaPedido(int idLineaPedido, int cantidad, double subtotal, double ptotal, double penvio, double total, Pedido pedido, Producto producto) {
        this.idLineaPedido = idLineaPedido;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.ptotal = ptotal;
        this.penvio = penvio;
        this.total = total;
        this.pedido = pedido;
        this.producto = producto;
    }

    public int getIdLineaPedido() {
        return idLineaPedido;
    }

    public void setIdLineaPedido(int idLineaPedido) {
        this.idLineaPedido = idLineaPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPtotal() {
        return ptotal;
    }

    public void setPtotal(double ptotal) {
        this.ptotal = ptotal;
    }

    public double getPenvio() {
        return penvio;
    }

    public void setPenvio(double penvio) {
        this.penvio = penvio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "idLineaPedido=" + idLineaPedido +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", ptotal=" + ptotal +
                ", penvio=" + penvio +
                ", total=" + total +
                ", pedido=" + pedido +
                ", producto=" + producto +
                '}';
    }
}
