package org.portada.modelo;

public class Modulo {

    private Long id;


    private String nombre;

    public Modulo() {
    }

    public Modulo(Long id) {
        this.id = id;
    }

    public Modulo(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
