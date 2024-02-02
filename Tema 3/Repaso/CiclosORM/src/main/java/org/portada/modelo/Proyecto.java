package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "proyecto", schema = "ciclosorm")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @ManyToMany
    @JoinTable(name = "proyecto_departamento", schema = "ciclosorm", joinColumns = @JoinColumn(name = "id_departamento", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_proyecto", referencedColumnName = "id"))
    private Collection<Departamento> departamento = new HashSet<Departamento>();

    public Proyecto() {
    }

    public Proyecto(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Departamento> getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Collection<Departamento> departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyecto proyecto = (Proyecto) o;
        return Objects.equals(id, proyecto.id) && Objects.equals(nombre, proyecto.nombre) && Objects.equals(departamento, proyecto.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, departamento);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
