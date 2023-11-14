package repasoacdt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Prestamo {

    private int PrestamoID;
    private int LibroID;
    private String FechaPrestamo;
    private String FechaDevolucion;

    public Prestamo(int PrestamoID, int LibroID, String FechaPrestamo, String FechaDevolucion) {
        this.PrestamoID = PrestamoID;
        this.LibroID = LibroID;
        this.FechaPrestamo = FechaPrestamo;
        this.FechaDevolucion = FechaDevolucion;
    }

    public Prestamo() {
    }

    public int getPrestamoID() {
        return PrestamoID;
    }

    public int getLibroID() {
        return LibroID;
    }

    public String getFechaPrestamo() {
        return FechaPrestamo;
    }

    public String getFechaDevolucion() {
        return FechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "PrestamoID=" + PrestamoID + ", LibroID=" + LibroID + ", FechaPrestamo=" + FechaPrestamo + ", FechaDevolucion=" + FechaDevolucion + '}';
    }

    public static int insertDB(Connection c, int prestamoID, int libroID, String fechaPrestamo, String fechaDevolucion) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO Prestamos (PrestamoID, LibroID, FechaPrestamo, FechaDevolucion) VALUES (?, ?, ?, ?)")) {
            c.setAutoCommit(true); //Lo desactive en una transaccion antigua y si quiero que lo suba sin poner c.commit tengo que activarlo de nuevo.
            ps.setInt(1, prestamoID);
            ps.setInt(2, libroID);
            ps.setString(3, fechaPrestamo);
            ps.setString(4, fechaDevolucion);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas;
        }
    }

}
