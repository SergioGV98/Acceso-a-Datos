package org.portada.modelo;


import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "proyecto", schema = "empresa_proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToMany
    @JoinTable(name = "departamentos_proyectos", catalog = "empresa_proyectos",
            joinColumns = {@JoinColumn(name = "proyecto_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "departamento_id", referencedColumnName = "id")})
    private Collection<Departamento> departamentos = new HashSet<Departamento>();


    public Proyecto() {
    }

    public Proyecto(String nombre) {
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
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
