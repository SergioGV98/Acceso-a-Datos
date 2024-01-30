package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado", schema = "empresa_proyectos")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false)
    private int idEmpleado;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "numero_empleado")
    private int numEmp;
    @ManyToOne
    @Column(name = "empleado_departamento")
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "id_coche_empresa")
    private CocheEmpresa coche;
}
