package actividad;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2_7 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            int productosInsertados = Producto.insertCSVDB(c, "productos.csv");
            System.out.printf("Productos insertados %d\n", productosInsertados);
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
