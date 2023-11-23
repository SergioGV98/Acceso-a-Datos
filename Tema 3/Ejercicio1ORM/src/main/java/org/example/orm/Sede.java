package org.example.orm;

import jakarta.persistence.*;

@Entity
public class Sede {
    private Integer idSede;
    private String nomSede;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sede", nullable = false)
    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    @Basic
    @Column(name = "nom_sede", nullable = false, length = 20)
    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sede sede = (Sede) o;

        if (idSede != null ? !idSede.equals(sede.idSede) : sede.idSede != null) return false;
        if (nomSede != null ? !nomSede.equals(sede.nomSede) : sede.nomSede != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSede != null ? idSede.hashCode() : 0;
        result = 31 * result + (nomSede != null ? nomSede.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "idSede=" + idSede +
                ", nomSede='" + nomSede + '\'' +
                '}';
    }
}
