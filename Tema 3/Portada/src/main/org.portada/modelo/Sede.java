package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "sede", schema = "empresa_proyectos")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede", nullable = false)
    private int idSede;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Collection<Departamento> departamentos;



}
