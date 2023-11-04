package actividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2_5 {

    public static void main(String[] args) {
        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            String[] dnis = {"12345678G", "87654321E", "12345678A", "23456789B"};

            for (String unDni : dnis) {
                Clientes c1 = Clientes.getDB(unDni, c);

                if (c1 != null) {
                    System.out.printf("Datos de cliente con DNI %s: %s\n", unDni, c1);
                } else {
                    System.out.printf("No existe cliente DNI %s\n", unDni);
                }
            }

            System.out.printf("Numero de clientes %d\n", Clientes.getNumClienteDB(c));

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
