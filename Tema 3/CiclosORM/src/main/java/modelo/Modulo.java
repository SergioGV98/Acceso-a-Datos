package modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "modulo", schema = "ciclosorm")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "modulo")
    private Collection<Alumno> alumnos;



}
