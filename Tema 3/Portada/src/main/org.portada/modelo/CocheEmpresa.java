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
    @Column(name = "coche_empleado")
    private Empleado empleado;

    
}
