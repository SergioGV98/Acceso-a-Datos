package actividad2.pkg2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Actividad22 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        
        int cod_prod = 1;
        String [] sentencias = {"insert into productos (cod_prod, nom_prod, pr_unit, descr) values ('"+cod_prod+"', 'Cerveza', '0.91', 'Una cerveza de origen italiana.');" +
                "insert into productos (cod_prod, nom_prod, pr_unit, descr) values ('"+cod_prod+"', 'Aceitunas', '0.5', 'Aceituna española.');"+
                "insert into productos (cod_prod, nom_prod, pr_unit, descr) values ('"+cod_prod+"', 'Cacahuetes', '1', 'Unos simples cacahuetes.');"};

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement()) {
           int nFil = 0;
            for(int i = 0; i < sentencias.length; i++){
                nFil = s.executeUpdate(sentencias[i]);
                cod_prod++;
            }
            System.out.printf("%d filas insertadas.\n", nFil);
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
