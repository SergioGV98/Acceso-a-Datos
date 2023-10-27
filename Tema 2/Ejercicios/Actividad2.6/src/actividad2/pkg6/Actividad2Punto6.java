package actividad2.pkg6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

            try (var fr = new FileReader("productos.csv"); var br = new BufferedReader(fr)) {

                String linea;

                while ((linea = br.readLine()) != null) {
                    try (PreparedStatement ps = c.prepareStatement("INSERT INTO productos (cod_prod, nom_prod, pr_unit, descr) VALUES (?,?,?,?);")) {
                        String[] campos = linea.split("\\|");
                        String cod_prod = "";
                        String nom_prod = "";
                        for(int i = 0; i < campos.length; i++){
                            cod_prod = campos[0];
                            nom_prod = campos[1];
                        }

                        /*
                         ps.setInt(1, Integer.parseInt(campo));
                            ps.setString(2, campo);
                            ps.setDouble(3, Double.parseDouble(campo));
                            ps.setString(4, campo);
                        */
                    }
                }
            }
        } catch (IOException | SQLException e) {
            System.out.printf("ERROR: %s\n", e.getMessage());
        }
    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
