package com.example.prueba.model;

import javax.persistence.*;

@Entity
public class Provincia {

    @Id
    private String idProvincia;
     @Column
    private String nombre;

    public Provincia() {
    }

    public Provincia(String idProvincia, String nombre) {
        this.idProvincia = idProvincia;
        this.nombre = nombre;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "idProvincia='" + idProvincia + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
