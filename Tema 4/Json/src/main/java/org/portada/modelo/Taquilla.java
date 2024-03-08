package org.portada.modelo;

import org.portada.modelo.Alumno;

public class Taquilla {

    private Long id;

    private Long num;

    private Alumno alumno;

    public Taquilla(Long id) {
        this.id = id;
    }

    public Taquilla(Long id, Long num, Alumno alumno) {
        this.id = id;
        this.num = num;
        this.alumno = alumno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "Taquilla{" +
                "id=" + id +
                ", num=" + num +
                ", alumno=" + alumno +
                '}';
    }
}
