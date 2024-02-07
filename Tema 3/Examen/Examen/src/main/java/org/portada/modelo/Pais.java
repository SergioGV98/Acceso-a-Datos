package org.portada.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "pais", schema = "paises_orm")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Basic
    @Column(name = "poblacion", nullable = true)
    private int poblacion;

    @Basic
    @Column(name = "pib", nullable = true)
    private BigDecimal pib;

    @OneToOne
    @JoinColumn(name = "presidente")
    private Presidente presidente;

    @ManyToMany
    @JoinTable(name = "pais_lengua", catalog = "paises_orm",
            joinColumns = {@JoinColumn(name = "pais_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "lengua_id", referencedColumnName = "id")})
    private Collection<Lengua> lenguas = new HashSet<Lengua>();

    @OneToMany(mappedBy = "pais")
    private Collection<Region> regiones = new HashSet<Region>();

    public Pais() {
    }

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public BigDecimal getPib() {
        return pib;
    }

    public void setPib(BigDecimal pib) {
        this.pib = pib;
    }

    public Presidente getPresidente() {
        return presidente;
    }

    public void setPresidente(Presidente presidente) {
        this.presidente = presidente;
    }

    public Collection<Lengua> getLenguas() {
        return lenguas;
    }

    public void setLenguas(Collection<Lengua> lenguas) {
        this.lenguas = lenguas;
    }

    public Collection<Region> getRegiones() {
        return regiones;
    }

    public void setRegiones(Collection<Region> regiones) {
        this.regiones = regiones;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", poblacion=" + poblacion +
                ", pib=" + pib +
                ", presidente=" + presidente +
                '}';
    }
}
