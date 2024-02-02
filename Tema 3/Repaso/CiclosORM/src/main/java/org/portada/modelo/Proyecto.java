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
    @JoinTable(name = "proyecto_department", catalog = "ciclosorm",
            joinColumns = {@JoinColumn(name = "id_proyecto", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "departamento_id", referencedColumnName = "id")})
    private Collection<Empleado> empleados = new HashSet<Empleado>();


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

    public Collection<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Collection<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proyecto proyecto)) return false;
        return Objects.equals(id, proyecto.id) && Objects.equals(nombre, proyecto.nombre) && Objects.equals(empleados, proyecto.empleados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, empleados);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
