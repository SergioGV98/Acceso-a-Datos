package org.portada.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado", schema = "empresa_proyectos")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Basic
    @Column(name = "numEmp")
    private Integer numEmp;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "datos_profesionales_id")
    private DatosProfesionales datosProfesionales;

    public Empleado() {
    }

    public Empleado(String nombre, Integer numEmp) {
        this.nombre = nombre;
        this.numEmp = numEmp;
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

    public Integer getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(Integer numEsp) {
        this.numEmp = numEsp;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamentos) {
        this.departamento = departamentos;
    }


    public DatosProfesionales getDatosProfesionales() {
        return datosProfesionales;
    }

    public void setDatosProfesionales(DatosProfesionales datosProfesionales) {
        this.datosProfesionales = datosProfesionales;

        this.datosProfesionales.setIdEmp(numEmp);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numEmp=" + numEmp +
                ", departamentos=" + departamento +
                ", datosProfesionales=" + datosProfesionales +
                '}';
    }
}
