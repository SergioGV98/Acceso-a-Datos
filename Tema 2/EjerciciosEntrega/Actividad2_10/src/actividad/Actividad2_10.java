package actividad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Actividad2_10 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            try (var br = new BufferedReader(new FileReader("monedas.csv"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    try {
                        PreparedStatement sInsert = c.prepareStatement("insert into monedas (cod_monedas, nom_moneda) values (?, ?)");
                        c.setAutoCommit(false);
                        int i = 1;
                        sInsert.setString(i++, linea.substring(0, linea.indexOf(",")));
                        sInsert.setString(i++, linea.substring(linea.indexOf(",")+1, linea.length()));
                        sInsert.executeUpdate();
                        c.commit();
                        System.out.printf("Insertada moneda (%s) exitosa.\n", linea.substring(linea.indexOf(",") + 1));

                    } catch (Exception ex) {
                        if (ex instanceof SQLException) {
                            muestraErrorSQL((SQLException) ex);
                        } else {
                            System.out.printf("ERROR: %s\n", ex.getMessage());
                        }

                        try {
                            c.rollback();
                            System.out.println("Se hace ROLLBACK");
                        } catch (SQLException exr) {
                            System.out.printf("ERROR en rollback: %s.\n", exr.getMessage());
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.printf("ERROR: %s.\n", ex.getMessage());
            }

        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
