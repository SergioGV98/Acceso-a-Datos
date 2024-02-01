package org.portada.modelo;

import jakarta.persistence.*;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return numEmp == empleado.numEmp && Objects.equals(id, empleado.id) && Objects.equals(nombre, empleado.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numEmp);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numEmp=" + numEmp +
                '}';
    }
}
