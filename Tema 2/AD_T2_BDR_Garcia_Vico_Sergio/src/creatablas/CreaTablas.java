package creatablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreaTablas {

    public static void main(String[] args) {

        String basedatos = "traducciones";
        String user = "traducciones";
        String pwd = "traducciones";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        String[] sentencias = {"create table idioma(cod_iso char(2) not null, nom_idioma char(16) not null, primary key(cod_iso));",
            "create table traductor(dni_nie char(9) not null, nom_trad varchar(50) not null, id_lengua_nativa char(2) not null, foreign key fk_trad_leng_nat(id_lengua_nativa) references idioma(cod_iso), primary key(dni_nie));",
            "create table texto(id_texto integer not null auto_increment,num_palabras integer,cod_idioma char(2) not null, primary key(id_texto), foreign key fk_texto_idioma(cod_idioma) references idioma(cod_iso));",
            "create table asig_traduccion(id_texto integer not null,cod_idioma_dest char(2) not null,dni_nie_trad char(9) not null,foreign key fk_asig_trad_texto(id_texto) references texto(id_texto),foreign key fk_asig_trad_idioma_dest(cod_idioma_dest) references idioma(cod_iso),foreign key fk_asig_trad_trad(dni_nie_trad) references traductor(dni_nie),primary key(id_texto, cod_idioma_dest));"};

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); var tablas = c.createStatement()) {
            for (String s : sentencias) {
                try {
                    tablas.execute(s);
                    System.out.printf("Sentencia %s | Creada con exito\n", s);
                } catch (SQLException e) {
                    System.out.printf("ERROR: %s\n", e.getMessage());
                }
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
