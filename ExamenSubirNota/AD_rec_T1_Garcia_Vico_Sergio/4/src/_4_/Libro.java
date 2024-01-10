
package _4_;

import java.io.Serializable;

public class Libro implements Serializable {

    private String titulo;
    private String autor;
    private String lengua;

    public Libro(String titulo, String autor, String lengua) {
        this.titulo = titulo;
        this.autor = autor;
        this.lengua = lengua;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLengua() {
        return lengua;
    }

    public void setLengua(String lengua) {
        this.lengua = lengua;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", lengua=" + lengua + '}';
    }
    
}
