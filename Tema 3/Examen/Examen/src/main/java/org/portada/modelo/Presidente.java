package org.portada.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "presidente", schema = "paises_orm")
public class Presidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Presidente() {
    }

    public Presidente(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Presidente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
