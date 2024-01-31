package modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cocheempresa", schema = "empresa_proyectos")
public class CocheEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coche_empresa", nullable = false)
    private int idCocheEmpresa;
    @Basic
    @Column(name = "matricula")
    private String matricula;
    @Basic
    @Column(name = "asignacion")
    private LocalDate asignadoHasta;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "coche_empleado")
    private Empleado empleado;

    public CocheEmpresa() {
    }

    public CocheEmpresa(String matricula, LocalDate asignadoHasta, Empleado empleado) {
        this.matricula = matricula;
        this.asignadoHasta = asignadoHasta;
        this.empleado = empleado;
    }

    public int getIdCocheEmpresa() {
        return idCocheEmpresa;
    }

    public void setIdCocheEmpresa(int idCocheEmpresa) {
        this.idCocheEmpresa = idCocheEmpresa;
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
                "idCocheEmpresa=" + idCocheEmpresa +
                ", matricula='" + matricula + '\'' +
                ", asignadoHasta=" + asignadoHasta +
                ", empleado=" + empleado +
                '}';
    }
}
