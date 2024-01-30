package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "proyecto", schema = "empresa_proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto", nullable = false)
    private int idProyecto;
    @ManyToMany
    @JoinColumn(name = "id_departamento", nullable = false)
    private Collection<Departamento> departamentos;
}
