package modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "alumno", schema = "ciclosorm")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Basic
    @Column(name = "fechaNacimiento")
    private LocalDate fechaNac;

    @ManyToOne
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    @ManyToMany
    @JoinTable(name = "matriculas", catalog = "ciclosorm",
            joinColumns = {@JoinColumn(name = "id_alumno", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_modulo", referencedColumnName = "id")})
    private Collection<Modulo> modulos;

    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    public Alumno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Collection<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(Collection<Modulo> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", grupo=" + grupo +
                ", modulos=" + modulos +
                '}';
    }
}
