package creatraductores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreaTraductores {

    public static void main(String[] args) {
        String basedatos = "traducciones";
        String user = "traducciones";
        String pwd = "traducciones";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            Object[][] datosTraductores = {
                {"37515445G", "LOPEZ", "es"},
                {"X5353636P", "ROSSI", "it"},
                {"73453363W", "SCHMIDT", "de"}};

            //YO CREE LOS IDIOMAS DESDE EL PROGRAMA, TE LO DEJO COMENTADO POR SI LO QUIERES VER, SI NO IGNORALO
            /*try (var insertIdiomas = c.createStatement()) {
                String[] idiomas = {"INSERT INTO `traducciones`.`idioma` (`cod_iso`, `nom_idioma`) VALUES ('de', 'destch');",
                    "INSERT INTO `traducciones`.`idioma` (`cod_iso`, `nom_idioma`) VALUES ('es', 'español');",
                    "INSERT INTO `traducciones`.`idioma` (`cod_iso`, `nom_idioma`) VALUES ('it', 'italiano');"};
                for (String s : idiomas) {
                    try {
                        insertIdiomas.execute(s);
                        System.out.printf("Sentencia %s | Creada con exito\n", s);
                    } catch (SQLException e) {
                        System.out.printf("ERROR: %s\n", e.getMessage());
                    }
                }
            }*/

            c.setAutoCommit(false);

            for (Object[] datos : datosTraductores) {
                String dni_nie = (String) datos[0];
                String nom_trad = (String) datos[1];
                String id_lengua_nativa = (String) datos[2];
                Traductor traductor = new Traductor(dni_nie, nom_trad, id_lengua_nativa);
                try{
                    if(traductor.insertDB(c)){
                        System.out.printf("Traductor %s con dni: %s y lengua nativa %s. Creado con exito.\n", nom_trad, dni_nie, id_lengua_nativa);
                    } else {
                        System.out.println("Error al crear el traductor.");
                    }
                }  catch (Exception ex) {
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
            c.commit();

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
