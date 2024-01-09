package repaso.objetos;

import java.io.Serializable;

public class Libro implements Serializable{
    
    private String titulo;
    private String autor;
    private int nPaginas;

    public Libro(String titulo, String autor, int nPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.nPaginas = nPaginas;
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

    public int getnPaginas() {
        return nPaginas;
    }

    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", nPaginas=" + nPaginas + '}';
    }
}
