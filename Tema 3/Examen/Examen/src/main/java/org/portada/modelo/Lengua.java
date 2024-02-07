package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "lengua", schema = "paises_orm")
public class Lengua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "cod_iso", nullable = false, unique = true, length = 2)
    private String codIso;

    @Basic
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public Lengua() {
    }

    public Lengua(String codIso, String nombre) {
        this.codIso = codIso;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodIso() {
        return codIso;
    }

    public void setCodIso(String codIso) {
        this.codIso = codIso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Lengua{" +
                "id=" + id +
                ", codIso='" + codIso + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
