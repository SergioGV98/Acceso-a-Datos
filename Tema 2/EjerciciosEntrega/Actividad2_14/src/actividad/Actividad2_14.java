package actividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actividad2_14 {

    public static void main(String[] args) {
        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;
        
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            System.out.printf("Facturas del cliente con DNI %s: %d\n", "12345678A", numeroFacturas(c, "12345678A"));
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

    public static int numeroFacturas(Connection c, String dni) throws SQLException {
        /*
        DELIMITER //
        CREATE FUNCTION numero_facturas (in_dni varchar(24))
        RETURNS INTEGER DETERMINISTIC
        BEGIN
            DECLARE num_fact INTEGER;
            SELECT COUNT(num_factura) into num_fact FROM facturas where dni = in_dni;
            RETURN(num_fact);
        END //
        DELIMITER ;
         */
        PreparedStatement ps = c.prepareStatement("select numero_facturas(?)");
        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

}
