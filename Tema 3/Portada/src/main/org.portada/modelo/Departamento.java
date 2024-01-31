package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "departamento", schema = "empresa_proyectos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento", nullable = false)
    private int idDeparamento;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
    private Sede sede;
    @ManyToMany
    @JoinColumn(name = "idProyecto", nullable = false)
    private Collection<Proyecto> proyectos;
    @OneToMany
    @JoinColumn(name = "id_empleado", nullable = false)
    private Collection<Empleado> empleados;

    public Departamento(int idDeparamento, String nombre, Sede sede, Collection<Proyecto> proyectos, Collection<Empleado> empleados) {
        this.idDeparamento = idDeparamento;
        this.nombre = nombre;
        this.sede = sede;
        this.proyectos = proyectos;
        this.empleados = empleados;
    }

    public int getIdDeparamento() {
        return idDeparamento;
    }

    public void setIdDeparamento(int idDeparamento) {
        this.idDeparamento = idDeparamento;
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

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Collection<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Collection<Proyecto> proyectos) {
        this.proyectos = proyectos;
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
                "idDeparamento=" + idDeparamento +
                ", nombre='" + nombre + '\'' +
                ", sede=" + sede +
                ", proyectos=" + proyectos +
                ", empleados=" + empleados +
                '}';
    }
}
