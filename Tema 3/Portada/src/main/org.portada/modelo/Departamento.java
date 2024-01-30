package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "departamento", schema = "empresa_proyectos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento", nullable = false)
    private int idDeparamento;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany
    @Column(name = "sede")
    private Sede sede;
    @ManyToMany
    @JoinColumn(name = "idProyecto", nullable = false)
    private Collection<Proyecto> proyectos;
    @OneToMany
    @JoinColumn(name = "id_empleado", nullable = false)
    private Collection<Empleado> empleados;
}
