package repasoacdt2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Libro {

    private int libroID;
    private String titulo;
    private int autorID;
    private int generoID;

    public Libro(int libroID, String titulo, int autorID, int generoID) {
        this.libroID = libroID;
        this.titulo = titulo;
        this.autorID = autorID;
        this.generoID = generoID;
    }

    public int getLibroID() {
        return libroID;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAutorID() {
        return autorID;
    }

    public int getGeneroID() {
        return generoID;
    }

    @Override
    public String toString() {
        return "Libro{" + "libroID=" + libroID + ", titulo=" + titulo + ", autorID=" + autorID + ", generoID=" + generoID + '}';
    }

    public static boolean ingresarLibrosCSV(Connection c, String ruta) throws FileNotFoundException, IOException {

        try (var br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            String[] encabezado = br.readLine().split(",");

            while ((linea = br.readLine()) != null) {
                try (PreparedStatement insert = c.prepareStatement("INSERT INTO libros (LibroID, Titulo, AutorID, GeneroID) VALUES (?, ?, ?, ?)")) {
                    c.setAutoCommit(false);
                    int i = 1;
                    String[] libro = linea.split(",");
                    insert.setInt(i++, Integer.parseInt(libro[0]));
                    insert.setString(i++, libro[1]);
                    insert.setInt(i++, Integer.parseInt(libro[2]));
                    insert.setInt(i++, Integer.parseInt(libro[3]));
                    insert.executeUpdate();
                    System.out.printf("%s: %s con %s: %s\n", encabezado[0], libro[0], encabezado[1], libro[1]);
                } catch (Exception ex) {
                    if (ex instanceof SQLException) {
                        muestraErrorSQL((SQLException) ex);
                    } else {
                        System.out.printf("ERROR: %s\n", ex.getMessage());
                    }

                    try {
                        c.rollback();
                        System.out.println("Se hace ROLLBACK");
                    } catch (SQLException exr) {
                        System.out.printf("ERROR en rollback: %s.\n", exr.getMessage());
                    }
                }
            }

            try {
                c.commit();
            } catch (SQLException exc) {
                System.out.printf("ERROR en commit: %s.\n", exc.getMessage());
            }
        }

        return true;
    }

    public static String consultarGeneroLibro(Connection c, String titulo) throws SQLException {
        String genero = null;
        //"SELECT g.Nombre AS NombreGenero FROM libros l JOIN generos g ON l.GeneroID = g.GeneroID WHERE l.Titulo = ?" MANERA DIFICIL
        try (PreparedStatement consulta = c.prepareStatement("SELECT (SELECT Nombre FROM generos WHERE GeneroID = l.GeneroID) AS NombreGenero FROM libros l WHERE l.Titulo = ?")) {
            consulta.setString(1, titulo);

            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    genero = resultado.getString("NombreGenero");
                }
            }
        }
        return genero;
    }

    public static String consultarAutorLibro(Connection c, String titulo) throws SQLException {
        String autor = null;

        try (PreparedStatement consulta = c.prepareStatement("SELECT (SELECT Nombre FROM autores WHERE AutorID = l.AutorID) AS NombreAutor FROM libros l WHERE l.Titulo = ?")) {
            consulta.setString(1, titulo);

            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    autor = resultado.getString("NombreAutor");
                }
            }
        }
        return autor;
    }

    public static boolean convertirTitulosMayusculas(Connection c) throws SQLException {

        try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT LibroID, Titulo FROM libros")) {
            c.setAutoCommit(false);
            while (rs.next()) {
                int libroID = rs.getInt("LibroID");
                String titulo = rs.getString("Titulo");
                try (PreparedStatement update = c.prepareStatement("UPDATE libros SET Titulo = ? WHERE LibroID = ?")) {
                    update.setString(1, titulo.toUpperCase());
                    //update.setString(1, titulo.substring(0, 1).toUpperCase() + titulo.substring(1).toLowerCase());
                    update.setInt(2, libroID);
                    update.executeUpdate();
                } catch (Exception ex) {
                    if (ex instanceof SQLException) {
                        muestraErrorSQL((SQLException) ex);
                    } else {
                        System.out.printf("ERROR: %s\n", ex.getMessage());
                    }
                    try {
                        c.rollback();
                        System.out.println("Se hace ROLLBACK");
                    } catch (SQLException exr) {
                        System.out.printf("ERROR en rollback: %s.\n", exr.getMessage());
                    }
                }
            }
            c.commit();
        }
        return true;
    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
