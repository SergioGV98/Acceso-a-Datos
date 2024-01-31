package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "proyecto", schema = "empresa_proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto", nullable = false)
    private int idProyecto;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany
    @JoinColumn(name = "id_departamento", nullable = false)
    private Collection<Departamento> departamentos;

    public Proyecto(int idProyecto, String nombre, Collection<Departamento> departamentos) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.departamentos = departamentos;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
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
                "idProyecto=" + idProyecto +
                ", nombre='" + nombre + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }
}
