package com.example.prueba.model;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String nombreEmpresa; //opcional
    @Column
    private String pais;
    @Column
    private String direccion;
    @Column
    private String apartamento; //numero de piso
    @Column
    private int codigoPostal; //5 numeros
    @Column
    private String localidad;
    @Column
    private int telefono;
    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name="id_provincia", foreignKey = @ForeignKey(name = "Fk_provincia"))
    Provincia provincia;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellidos, String nombreEmpresa, String pais, String direccion, String apartamento, int codigoPostal, String localidad, int telefono, String email, Provincia provincia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreEmpresa = nombreEmpresa;
        this.pais = pais;
        this.direccion = direccion;
        this.apartamento = apartamento;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.telefono = telefono;
        this.email = email;
        this.provincia = provincia;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", pais='" + pais + '\'' +
                ", direccion='" + direccion + '\'' +
                ", apartamento='" + apartamento + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", localidad='" + localidad + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", provincia=" + provincia +
                '}';
    }
}
