package org.portada.modelo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class Alumno {

    private Long id;

    private String nombre;

    private LocalDate fechaNac;

    private Collection<Modulo> alumnos = new HashSet<Modulo>();

    public Alumno(Long id) {
        this.id = id;
    }

    public Alumno(Long id, String nombre, LocalDate fechaNac, Collection<Modulo> alumnos) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.alumnos = alumnos;
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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Collection<Modulo> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Collection<Modulo> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", alumnos=" + alumnos +
                '}';
    }
}
