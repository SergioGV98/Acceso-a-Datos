package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "departamento", schema = "ciclosorm")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @OneToMany()
    @JoinColumn(name = "departamento_id")
    private Collection<Empleado> empleado = new HashSet<Empleado>();

    public Departamento() {
    }

    public Departamento(String nombre) {
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

    public Collection<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Collection<Empleado> empleado) {
        this.empleado = empleado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(empleado, that.empleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, empleado);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", empleado=" + empleado +
                '}';
    }
}
