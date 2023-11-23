package org.example.orm;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Proyecto {
    private Integer idProy;
    private Date fInicio;
    private Date fFin;
    private String nomProy;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_proy", nullable = false)
    public Integer getIdProy() {
        return idProy;
    }

    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }

    @Basic
    @Column(name = "f_inicio", nullable = false)
    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    @Basic
    @Column(name = "f_fin", nullable = true)
    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }

    @Basic
    @Column(name = "nom_proy", nullable = false, length = 20)
    public String getNomProy() {
        return nomProy;
    }

    public void setNomProy(String nomProy) {
        this.nomProy = nomProy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proyecto proyecto = (Proyecto) o;

        if (idProy != null ? !idProy.equals(proyecto.idProy) : proyecto.idProy != null) return false;
        if (fInicio != null ? !fInicio.equals(proyecto.fInicio) : proyecto.fInicio != null) return false;
        if (fFin != null ? !fFin.equals(proyecto.fFin) : proyecto.fFin != null) return false;
        if (nomProy != null ? !nomProy.equals(proyecto.nomProy) : proyecto.nomProy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProy != null ? idProy.hashCode() : 0;
        result = 31 * result + (fInicio != null ? fInicio.hashCode() : 0);
        result = 31 * result + (fFin != null ? fFin.hashCode() : 0);
        result = 31 * result + (nomProy != null ? nomProy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProy=" + idProy +
                ", fInicio=" + fInicio +
                ", fFin=" + fFin +
                ", nomProy='" + nomProy + '\'' +
                '}';
    }
}
