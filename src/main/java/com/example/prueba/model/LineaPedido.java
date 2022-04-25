package com.example.prueba.model;

import javax.persistence.*;

@Entity
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLineaPedido;
    @Column
    private int cantidad;
    @Column
    private double precio; //decimal (5,2)

    @ManyToOne
    @JoinColumn(name = "id_Pedido", foreignKey = @ForeignKey(name = "FK_pedido"))
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_Producto", foreignKey = @ForeignKey(name = "FK_producto"))
    private Producto producto;

    public LineaPedido() {
    }

    public LineaPedido(int idLineaPedido, int cantidad, double precio, Pedido pedido, Producto producto) {
        this.idLineaPedido = idLineaPedido;
        this.cantidad = cantidad;
        this.precio = precio;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
                ", precio=" + precio +
                ", pedido=" + pedido +
                ", producto=" + producto +
                '}';
    }
}
