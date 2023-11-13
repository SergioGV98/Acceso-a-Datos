package repasoacdt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Autor {

    private int autorID;
    private String nombre;
    private String nacionalidad;

    public Autor(int AutorID, String Nombre, String Nacionalidad) {
        this.autorID = AutorID;
        this.nombre = Nombre;
        this.nacionalidad = Nacionalidad;
    }

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Autor() {
    }

    public int getAutorID() {
        return autorID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setAutorID(int AutorID) {
        this.autorID = AutorID;
    }

    @Override
    public String toString() {
        return "Autores{" + "AutorID=" + autorID + ", Nombre=" + nombre + ", Nacionalidad=" + nacionalidad + '}';
    }

    // INSERTAMOS Autores con clave generada en autorID, si lo volviese a ejecutar seguiria introduciendo Autores con el mismo nombre pero desde la posicion ultima en adelante.
    // Si no consulto previamente si existe.
    public Autor insertDB(Connection c, Autor autor) throws SQLException {
        Autor autorExistente = buscarAutorPorNombre(c, autor.getNombre());

        if (autorExistente != null) {
            System.out.printf("El autor %s ya existe, imposible introducir de nuevo en la base de datos.\n", autorExistente.getNombre());
            return null;
        }

        try (PreparedStatement ps = c.prepareStatement("INSERT INTO Autores (Nombre, Nacionalidad) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {

            int i = 1;
            ps.setString(i++, autor.getNombre());
            ps.setString(i++, autor.getNacionalidad());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    autor.setAutorID(rs.getInt(1));
                    return autor;
                }
            }
        }
        return null;
    }

    private Autor buscarAutorPorNombre(Connection c, String nombre) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT AutorID, Nombre, Nacionalidad FROM Autores WHERE Nombre = ?")) {
            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Autor(rs.getInt("AutorID"), rs.getString("Nombre"), rs.getString("Nacionalidad"));
                }
            }
        }

        return null;
    }

    public ArrayList<String> autoresOrdenadosAlfabeticamente(Connection c) throws SQLException {
        ArrayList<String> autoresOrdenados = new ArrayList<String>();
        try (PreparedStatement ps = c.prepareStatement("SELECT AutorID, Nombre, Nacionalidad FROM Autores ORDER BY Nombre ASC;")) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    autoresOrdenados.add(rs.getString("Nombre"));
                }
            }
        }
        return autoresOrdenados;
    }
    
    public ArrayList<String> autoresOrdenadosAlfabeticamente(Connection c, String orden) throws SQLException {
        ArrayList<String> autoresOrdenados = new ArrayList<String>();
        try (PreparedStatement ps = c.prepareStatement("SELECT AutorID, Nombre, Nacionalidad FROM Autores ORDER BY " + orden + " ASC;")) { // No necesita '" + numero + "' porque no es un numero

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    autoresOrdenados.add(rs.getString("Nombre"));
                }
            }
        }
        return autoresOrdenados;
    }
}
