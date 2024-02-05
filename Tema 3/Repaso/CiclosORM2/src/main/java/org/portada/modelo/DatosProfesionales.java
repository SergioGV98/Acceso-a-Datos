package org.portada.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "datos_profesionales", schema = "empresa_proyectos")
public class DatosProfesionales {


    @Id
    @Column(name = "idEmp", nullable = false, unique = true)
    private Integer idEmp;

    @Basic
    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @Basic
    @Column(name = "salBrutoAnual", nullable = false)
    private BigDecimal salBrutoAnual;

    public DatosProfesionales() {
    }

    public DatosProfesionales(String categoria, BigDecimal salBrutoAnual) {
        this.categoria = categoria;
        this.salBrutoAnual = salBrutoAnual;
    }

    public DatosProfesionales(Integer idEmp, String categoria, BigDecimal salBrutoAnual) {
        this.idEmp = idEmp;
        this.categoria = categoria;
        this.salBrutoAnual = salBrutoAnual;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getSalBrutoAnual() {
        return salBrutoAnual;
    }

    public void setSalBrutoAnual(BigDecimal salBrutoAnual) {
        this.salBrutoAnual = salBrutoAnual;
    }

    @Override
    public String toString() {
        return "DatosProfesionales{" +
                "idEmp=" + idEmp +
                ", categoria='" + categoria + '\'' +
                ", salBrutoAnual=" + salBrutoAnual +
                '}';
    }
}