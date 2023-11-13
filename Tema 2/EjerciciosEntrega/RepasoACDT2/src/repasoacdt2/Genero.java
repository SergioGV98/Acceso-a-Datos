package repasoacdt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Genero {

    private int generoID;
    private String nombre;

    public Genero(int generoID, String nombre) {
        this.generoID = generoID;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Genero{" + "generoID=" + generoID + ", nombre=" + nombre + '}';
    }

    public static int insertDB(Connection c, int generoID, String nombre) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO Generos (GeneroID, Nombre) VALUES (?, ?)")) {

            ps.setInt(1, generoID);
            ps.setString(2, nombre);

            return ps.executeUpdate();
        }
    }

    public static String consultarGenero(Connection c, int generoID) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT Nombre FROM Generos WHERE GeneroID = ?")) {
            ps.setInt(1, generoID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Nombre");
                } else {
                    // Devuelve un valor indicativo de que el género no se encontró
                    return "Género no encontrado";
                }
            }
        }
    }

}
