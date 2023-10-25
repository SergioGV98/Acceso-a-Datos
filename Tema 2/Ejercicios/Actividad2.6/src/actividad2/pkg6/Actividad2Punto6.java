package actividad2.pkg6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2Punto6 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        Productos pr = new Productos(1, "SACACORCHOS", 6.50, "SACACORCHOS ACERO");

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            pr.insertDB(c);
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
