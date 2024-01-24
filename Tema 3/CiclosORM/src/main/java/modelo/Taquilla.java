package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "taquilla", schema = "ciclosorm")
public class Taquilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "num", nullable = false, unique = true)
    private Long num;

}
