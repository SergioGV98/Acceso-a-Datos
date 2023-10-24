package actividad2.pkg3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad2Punto3 {

    public static void main(String[] args) {
        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        /*try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement()) {
            int nFil = s.executeUpdate(
                    "insert into clientes (cod_cliente, dni, nom_cliente) values"
                    + "(1, '12345678A','astorga'),"
                    + "(2, '23456789B','benítez'),"
                    + "(3, '34567890C','cervera')");
            System.out.printf("%d filas insertadas.\n", nFil);
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }*/
        String[] nombres = {"astorga", "benitez", "cerveza"};
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement()) {
            for (byte i = 1; i <= 3; i++) {
                s.executeUpdate("UPDATE 'pruebasprog'.'clientes' SET 'nom_clente' = '" + nombres[i].toUpperCase() + "' WHERE ('cod_cliente' = '" + i + "');");
            }
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
