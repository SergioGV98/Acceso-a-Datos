package actividad2.pkg6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actividad2Punto6 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        Productos[] productos = {new Productos(1, "SACACORCHOS", 6.50, "SACACORCHOS ACERO"),
            new Productos(2, "LAPIZ", 0.20, "LAPIZ DE MADERA"),
            new Productos(3, "BOLI", 0.40, null),
            new Productos(4, "GOMA", 6.50, "GOMA DE BORRAR GRANDE"),
            new Productos(5, "PORTAMINAS", 6.50, null)};

        /*
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {

            for (int i = 0; i < productos.length; i++) {
                if (productos[i].insertDB(c)) {
                    System.out.println("Se ha podido insertar el producto a la base de datos.");
                } else {
                    System.out.println("No se ha podido introducir el producto en la base de datos.");
                }
            }
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }*/
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            Productos producto = new Productos();
            producto.insertCSVDB(c, "productos.csv");
        } catch (SQLException e) {
            muestraErrorSQL(e);
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
