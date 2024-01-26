package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "taquilla", schema = "ciclosorm")
public class Taquilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "num", nullable = false, unique = true)
    private Long num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Taquilla{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
