package org.portada.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "alumno", schema = "instituto")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Basic
    @Column(name = "fechaNac", nullable = false)
    private LocalDate fechaNac;
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;
    @ManyToMany
    @JoinTable(name = "alumno_modulo", catalog = "instituto",
        joinColumns = {@JoinColumn(name = "alumno_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "modulo_id", referencedColumnName = "id")})
    private Collection<Modulo> modulos = new HashSet<Modulo>();


    public Alumno() {
    }

    public Alumno(String nombre, LocalDate fechaNac) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Collection<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(Collection<Modulo> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", grupo=" + grupo +
                '}';
    }
}
