package actividad2.pkg3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        List<Integer> codigosClientes = new ArrayList<>();
        List<String> nombresClientes = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement();) {
            ResultSet resultSet = s.executeQuery("SELECT cod_cliente, nom_cliente FROM pruebasprog.clientes");
            while (resultSet.next()) {
                int codCliente = resultSet.getInt("cod_cliente");
                String nombreCliente = resultSet.getString("nom_cliente");

                codigosClientes.add(codCliente);
                nombresClientes.add(nombreCliente);
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement();) {
            for (int i = 0; i < codigosClientes.size(); i++) {
                int codCliente = codigosClientes.get(i);
                String nombreCliente = nombresClientes.get(i);

                String nombreMayusculas = nombreCliente.toUpperCase();

                int filasActualizadas = s.executeUpdate("UPDATE pruebasprog.clientes SET nom_cliente = '" + nombreMayusculas + "' WHERE cod_cliente = " + codCliente);

                System.out.printf("Filas actualizadas para el cliente %d: %d\n", codCliente, filasActualizadas);
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
