package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "sede", schema = "empresa_proyectos")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "sede")
    private Collection<Departamento> departamentos = new HashSet<Departamento>();


    public Sede() {
    }

    public Sede(String nombre) {
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

    public Collection<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Collection<Departamento> departamentos) {
        this.departamentos = departamentos;
    }


    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
