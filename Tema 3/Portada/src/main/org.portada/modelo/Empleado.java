package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado", schema = "empresa_proyectos")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", insertable = false, updatable = false)
    private int idEmpleado;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "numero_empleado")
    private int numEmp;
    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_coche_empresa")
    private CocheEmpresa coche;

    public Empleado(int idEmpleado, String nombre, int numEmp, Departamento departamento, CocheEmpresa coche) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.numEmp = numEmp;
        this.departamento = departamento;
        this.coche = coche;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public CocheEmpresa getCoche() {
        return coche;
    }

    public void setCoche(CocheEmpresa coche) {
        this.coche = coche;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", numEmp=" + numEmp +
                ", departamento=" + departamento +
                ", coche=" + coche +
                '}';
    }
}
