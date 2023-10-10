package conexionbd;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos
                + parAdic;
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            System.out.printf("Conexión establecida, BD %s, servidor %s versión %d.\n",
                    c.getCatalog(), c.getMetaData().getDatabaseProductName(),
                    c.getMetaData().getDatabaseMajorVersion());
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
