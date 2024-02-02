package org.portada.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cocheempresa", schema = "ciclosorm")
public class CocheEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "matricula")
    private String matricula;
    @Basic
    @Column(name = "asignadoHasta")
    private LocalDate asignadoHasta;

    @OneToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    public CocheEmpresa() {
    }

    public CocheEmpresa(String matricula, LocalDate asignadoHasta) {
        this.matricula = matricula;
        this.asignadoHasta = asignadoHasta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocheEmpresa that = (CocheEmpresa) o;
        return Objects.equals(id, that.id) && Objects.equals(matricula, that.matricula) && Objects.equals(asignadoHasta, that.asignadoHasta) && Objects.equals(empleado, that.empleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, asignadoHasta, empleado);
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
