package creatextos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreaTextos {

    public static void main(String[] args) {

        String basedatos = "traducciones";
        String user = "traducciones";
        String pwd = "traducciones";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            Texto t = new Texto(12584, "es");
            int codTexto = t.saveDB(c);
            Texto t1 = Texto.getDB(c, codTexto);
            System.out.printf("Texto %d, %d palabras en %s.\n", codTexto, t1.getNumPalabras(), t1.getCodIdioma());

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
