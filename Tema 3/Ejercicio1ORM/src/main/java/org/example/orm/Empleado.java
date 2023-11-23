package org.example.orm;

import jakarta.persistence.*;

@Entity
public class Empleado {
    private String dni;
    private String nomEmp;
    private Integer idDepto;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "nom_emp", nullable = false, length = 40)
    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    @Basic
    @Column(name = "id_depto", nullable = false)
    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;

        if (dni != null ? !dni.equals(empleado.dni) : empleado.dni != null) return false;
        if (nomEmp != null ? !nomEmp.equals(empleado.nomEmp) : empleado.nomEmp != null) return false;
        if (idDepto != null ? !idDepto.equals(empleado.idDepto) : empleado.idDepto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + (nomEmp != null ? nomEmp.hashCode() : 0);
        result = 31 * result + (idDepto != null ? idDepto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "dni='" + dni + '\'' +
                ", nomEmp='" + nomEmp + '\'' +
                ", idDepto=" + idDepto +
                '}';
    }
}
