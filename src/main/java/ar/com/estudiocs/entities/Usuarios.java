/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.estudiocs.entities;

import javax.persistence.*;


@Entity
@Table(name="usuarios")
public class Usuarios {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean inactivo;
    @Column(name="usuario")
    private String username;
    @Column(name="pass")
    private String password;
    @Column(name="pass_orig")
    private String passorig;

    @JoinColumn(name= "fk_rol", referencedColumnName = "id",nullable=false)
    @ManyToOne
    private Roles rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getInactivo() {
        return inactivo;
    }

    public void setInactivo(Boolean inactivo) {
        this.inactivo = inactivo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public String getPassorig() {
        return passorig;
    }

    public void setPassorig(String passorig) {
        this.passorig = passorig;
    }
}

