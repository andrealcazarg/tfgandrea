package com.example.prueba.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdProducto;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column(precision = 5, scale = 2)
    private double precio; //decimal (5,2)
    @Column(precision = 5, scale = 2)
    private double peso;
    @Column
    private boolean disponible;

    @Column
    private int cantidad;
    @Column
    @Nullable
    private String imagen;
    @ManyToOne
    @JoinColumn(name="id_categoria", foreignKey = @ForeignKey(name = "Fk_categoria"))
    Categoria categoria;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String descripcion, double precio, double peso, boolean disponible,int cantidad, String imagen, Categoria categoria) {
        IdProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.peso = peso;
        this.disponible = disponible;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "IdProducto=" + IdProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", peso=" + peso +
                ", disponible=" + disponible +
                ", cantidad=" + cantidad +
                ", imagen='" + imagen + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
