/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.estudiocs.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="clientes")
public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String apellido;
    private String nombre;
    private String nrodoc;
    private String direc;
    private String email;


    @JoinColumn(name = "fk_tipos_doc", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Tiposdoc tiposdoc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tiposdoc getTiposdoc() {
        return tiposdoc;
    }

    public void setTiposdoc(Tiposdoc tiposdoc) {
        this.tiposdoc = tiposdoc;
    }
}
