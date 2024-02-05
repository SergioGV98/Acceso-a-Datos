package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;


@Entity
@Table(name = "departamento", schema = "empresa_proyectos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    @OneToMany(mappedBy = "departamento")
    private Collection<Empleado> empleados = new HashSet<Empleado>();

    public Departamento() {
    }

    public Departamento(String nombre) {
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

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sedes) {
        this.sede = sedes;
    }

    public Collection<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Collection<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
