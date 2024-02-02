package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "ciclosorm")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "numEmp", nullable = false, unique = true)
    private int numEmp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @ManyToMany(mappedBy = "empleados")
    private Collection<Proyecto> proyectos;



    public Empleado() {
    }

    public Empleado(String nombre, int numEmp) {
        this.nombre = nombre;
        this.numEmp = numEmp;
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

    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Collection<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Collection<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Empleado empleado)) return false;
        return numEmp == empleado.numEmp && Objects.equals(id, empleado.id) && Objects.equals(nombre, empleado.nombre) && Objects.equals(departamento, empleado.departamento) && Objects.equals(proyectos, empleado.proyectos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numEmp, departamento, proyectos);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numEmp=" + numEmp +
                ", departamento=" + departamento +
                '}';
    }
}
