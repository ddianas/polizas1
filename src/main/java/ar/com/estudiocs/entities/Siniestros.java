/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.estudiocs.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="siniestros")
public class Siniestros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numtransaccion;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaocurrencia;
    private Double monto;
    private String observacion;

    @JoinColumn(name = "fk_clientes", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Clientes clientes;

    @JoinColumn (name = "fk_tipo_siniestro", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Tiposiniestro tiposiniestro;

    @JoinColumn(name = "fk_polizas", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Polizas polizas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(Integer numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public Date getFechaocurrencia() {
        return fechaocurrencia;
    }

    public void setFechaocurrencia(Date fechaocurrencia) {
        this.fechaocurrencia = fechaocurrencia;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Tiposiniestro getTiposiniestro() {
        return tiposiniestro;
    }

    public void setTiposiniestro(Tiposiniestro tiposiniestro) {
        this.tiposiniestro = tiposiniestro;
    }

    public Polizas getPolizas() {
        return polizas;
    }

    public void setPolizas(Polizas polizas) {
        this.polizas = polizas;
    }
}
