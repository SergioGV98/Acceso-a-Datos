package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "grupo", schema = "ciclosorm")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nombre", nullable = false, length = 60, unique = true)
    private String nombre;

    @Basic
    @Column(name = "maxAlumnos", nullable = false)
    private Integer maxAlumnos;

    @OneToMany(mappedBy = "grupo")
    private Collection<Alumno> alumnos;

    public Grupo(){

    }

    public Grupo(String nombre) {
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

    public Integer getMaxAlumnos() {
        return maxAlumnos;
    }

    public void setMaxAlumnos(Integer maxAlumnos) {
        this.maxAlumnos = maxAlumnos;
    }

    public Collection<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Collection<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", maxAlumnos=" + maxAlumnos +
                '}';
    }
}