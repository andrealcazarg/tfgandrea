package com.example.prueba.model;

import javax.persistence.*;

@Entity
public class Prueba {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
     private String idPrueba;
    @Column
     private String nombre;

    public Prueba() {
    }

    public Prueba(String idPrueba, String nombre) {
        this.idPrueba = idPrueba;
        this.nombre = nombre;
    }

    public String getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(String idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "idPrueba=" + idPrueba +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
