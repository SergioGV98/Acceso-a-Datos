package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "datosprofesionales", schema = "ciclosorm")
public class DatosProfesionales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmp", nullable = false)
    private Long idEmp;

    @Basic
    @Column(name = "categ")
    private String categ;
    @Basic
    @Column(name = "salBrutoAnual")
    private float salBrutoAnual;

    public DatosProfesionales() {
    }

    public DatosProfesionales(String categ, float salBrutoAnual) {
        this.categ = categ;
        this.salBrutoAnual = salBrutoAnual;
    }

    public Long getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Long idEmp) {
        this.idEmp = idEmp;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public float getSalBrutoAnual() {
        return salBrutoAnual;
    }

    public void setSalBrutoAnual(float salBrutoAnual) {
        this.salBrutoAnual = salBrutoAnual;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosProfesionales that = (DatosProfesionales) o;
        return Float.compare(salBrutoAnual, that.salBrutoAnual) == 0 && Objects.equals(idEmp, that.idEmp) && Objects.equals(categ, that.categ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmp, categ, salBrutoAnual);
    }

    @Override
    public String toString() {
        return "DatosProfesionales{" +
                "idEmp=" + idEmp +
                ", categ='" + categ + '\'' +
                ", salBrutoAnual=" + salBrutoAnual +
                '}';
    }
}
