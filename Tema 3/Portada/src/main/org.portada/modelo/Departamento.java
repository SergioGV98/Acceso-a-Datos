package modelo;

import jakarta.persistence.*;

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
}
