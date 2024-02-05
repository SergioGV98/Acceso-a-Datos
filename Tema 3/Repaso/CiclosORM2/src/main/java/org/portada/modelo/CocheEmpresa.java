package org.portada.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "coche_empresa", schema = "empresa_proyectos")
public class CocheEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "matricula", nullable = false, length = 100)
    private String matricula;

    @Basic
    @Column(name = "asignadoHasta", nullable = false, length = 100)
    private LocalDate asignadoHasta;

    @OneToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;


    public CocheEmpresa() {
    }

    public CocheEmpresa(String matricula) {
        this.matricula = matricula;
    }

    public CocheEmpresa(String matricula, LocalDate asignadoHasta) {
        this.matricula = matricula;
        this.asignadoHasta = asignadoHasta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getAsignadoHasta() {
        return asignadoHasta;
    }

    public void setAsignadoHasta(LocalDate asignadoHasta) {
        this.asignadoHasta = asignadoHasta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "CocheEmpresa{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", asignadoHasta=" + asignadoHasta +
                ", empleado=" + empleado +
                '}';
    }
}
