package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "datosprofesionales", schema = "empresa_proyectos")
public class DatosProfesionales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_empleado")
    private int idEmp;
    @Basic
    @Column(name = "categoria")
    private String categ;
    @Basic
    @Column(name = "salario")
    private Double salBrutoAnual;

    public DatosProfesionales(int idEmp, String categ, Double salBrutoAnual) {
        this.idEmp = idEmp;
        this.categ = categ;
        this.salBrutoAnual = salBrutoAnual;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public Double getSalBrutoAnual() {
        return salBrutoAnual;
    }

    public void setSalBrutoAnual(Double salBrutoAnual) {
        this.salBrutoAnual = salBrutoAnual;
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
