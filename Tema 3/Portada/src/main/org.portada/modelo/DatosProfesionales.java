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
}
