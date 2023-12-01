package org.example.orm;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Departamento {
    private Integer idDepto;
    private String nomDepto;
    //private Integer idSede;
    private Sede sedeByIdSede;
    private Collection<Empleado> empleadosByIdDepto;
    private Integer idSede;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_depto", nullable = false)
    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    @Basic
    @Column(name = "nom_depto", nullable = false, length = 32)
    public String getNomDepto() {
        return nomDepto;
    }

    /*@Basic
    @Column(name = "id_sede", nullable = false)
    public Integer getIdSede() {
        return idSede;
    }*/

    /*public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }*/

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departamento that = (Departamento) o;

        if (idDepto != null ? !idDepto.equals(that.idDepto) : that.idDepto != null) return false;
        if (nomDepto != null ? !nomDepto.equals(that.nomDepto) : that.nomDepto != null) return false;
        //if (idSede != null ? !idSede.equals(that.idSede) : that.idSede != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDepto != null ? idDepto.hashCode() : 0;
        result = 31 * result + (nomDepto != null ? nomDepto.hashCode() : 0);
        //result = 31 * result + (idSede != null ? idSede.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede", nullable = false)
    public Sede getSedeByIdSede() {
        return sedeByIdSede;
    }

    public void setSedeByIdSede(Sede sedeByIdSede) {
        this.sedeByIdSede = sedeByIdSede;
    }

    @OneToMany(mappedBy = "departamentoByIdDepto")
    public Collection<Empleado> getEmpleadosByIdDepto() {
        return empleadosByIdDepto;
    }

    public void setEmpleadosByIdDepto(Collection<Empleado> empleadosByIdDepto) {
        this.empleadosByIdDepto = empleadosByIdDepto;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDepto=" + idDepto +
                ", nomDepto='" + nomDepto + '\'' +
                '}';
    }

    @Basic
    @Column(name = "id_sede", nullable = false)
    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }
}
