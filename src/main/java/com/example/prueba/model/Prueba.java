package com.example.prueba.model;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@Entity
public class Prueba {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int idPrueba;
    @Column
     private String nombre;
    @Column
    private boolean confir;

    public Prueba() {
    }

    public Prueba(int idPrueba, String nombre,boolean confir) {
        this.idPrueba = idPrueba;
        this.nombre = nombre;
        this.confir = confir;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isConfir() {
        return confir;
    }

    public void setConfir(boolean confir) {
        this.confir = confir;
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "idPrueba=" + idPrueba +
                ", nombre='" + nombre + '\'' +
                ", confir=" + confir +
                '}';
    }
}
