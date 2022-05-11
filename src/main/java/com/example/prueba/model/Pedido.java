package com.example.prueba.model;

import com.example.prueba.constantes.Constante;

import javax.persistence.*;
import java.time.LocalDate;

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
    @Column
    private String sesionID;

    @Column(precision = 5, scale = 2)
    private double totalPedido;

    @Column(precision = 5, scale = 2)
    private double pEnvio;

    public Pedido() {
    }

   /* public Pedido(int idPedido, String fecha, boolean confir, String sesionID) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.confir = confir;
        this.sesionID = sesionID;
    }*/

    public Pedido(String fecha, boolean confir, String sesionID,double totalPedido, double pEnvio) {
        this.fecha = String.valueOf(LocalDate.now());
        this.confir = confir;
        this.sesionID = sesionID;
        this.totalPedido = totalPedido;
        this.pEnvio = pEnvio;
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

    public String getSesionID() {
        return sesionID;
    }
    public void setSesionID(String sesionID) {
        this.sesionID = sesionID;
    }

    public double getTotalPedido() {
        return totalPedido;
    }
    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public double getpEnvio() {
        return pEnvio;
    }

    public void setpEnvio(double pEnvio) {
        this.pEnvio = pEnvio;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fecha='" + fecha + '\'' +
                ", confir=" + confir +
                ", cliente=" + cliente +
                ", sesionID='" + sesionID + '\'' +
                ", totalPedido=" + totalPedido +
                ", pEnvio=" + pEnvio +
                '}';
    }
}
