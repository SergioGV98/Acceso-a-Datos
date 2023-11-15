package creaasigtraductores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreaAsigTraductores {

    public static void main(String[] args) {
        String basedatos = "traducciones";
        String user = "traducciones";
        String pwd = "traducciones";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {

            Texto t = new Texto(58634, "en");
            int codTexto = t.saveDB(c);
            HashMap asigTrad = new HashMap<String, String>();
            asigTrad.put("it", "X535363P");
            asigTrad.put("de", "73453363W");

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
