package org.portada.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "region", schema = "paises_orm")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    public Region() {
    }

    public Region(String nombre) {
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais=" + pais +
                '}';
    }
}
