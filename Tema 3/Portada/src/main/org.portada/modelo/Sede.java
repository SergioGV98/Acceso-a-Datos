package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "sede", schema = "empresa_proyectos")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede", nullable = false)
    private int idSede;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany
    @JoinColumn(name = "id_departamento", nullable = false)
    private Collection<Departamento> departamentos;

    public Sede() {
    }

    public Sede(String nombre, Collection<Departamento> departamentos) {
        this.nombre = nombre;
        this.departamentos = departamentos;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
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
                "idSede=" + idSede +
                ", nombre='" + nombre + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }
}
