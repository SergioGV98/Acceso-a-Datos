package actividad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2_13 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            borrarCliente(c, "34567890C");

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    public static void borrarCliente(Connection c, String dni) throws SQLException {
        /*
        DELIMITER //
        CREATE PROCEDURE borrar_cliente (in_dni varchar(24))
        BEGIN
            delete from clientes where dni = in_dni;
        END //
        DELIMITER ;
         */
        try (CallableStatement s = c.prepareCall("{call borrar_cliente(?)}")) {
            s.setString(1, dni);
            s.execute();
        }
    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
