package org.portada.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "taquilla", schema = "instituto")
public class Taquilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "num", nullable = false)
    private Integer num;

    @OneToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    public Taquilla() {
    }

    public Taquilla(Integer num) {
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
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
