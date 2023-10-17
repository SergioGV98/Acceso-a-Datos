package actvidad2.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Actvidad21 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos
                + parAdic;
        String[] sentencias = {"create table if not exists clientes(cod_cliente integer not null, dni char(9) not null, nom_cliente varchar(40) not null, primary key(cod_cliente));",
            "create unique index i_clientes_dni on clientes(dni);", "create if not exists table productos(cod_prod integer not null, nom_prod varchar(40) not null, pr_unit decimal(7,2) not null, descr varchar(120), primary key(cod_prod));",
            "create table if not exists facturas(num_factura int not null auto_increment, cod_cliente integer not null, fecha_factura date not null, primary key(num_factura), foreign key fk_fact_cli(cod_cliente) references clientes(cod_cliente));",
            "create table if not exists lineas_facturas(num_factura integer not null, lin_factura integer not null, cod_prod integer not null, cantidad decimal(10,3) not null, pr_unit decimal(9,3) not null, primary key(num_factura, lin_factura), foreign key fk_linfact_numfact(num_factura) references facturas(num_factura), foreign key fk_linfact_codprod(cod_prod) references productos(cod_prod));"};

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement()) {
            System.out.printf("Conexión establecida, BD %s, servidor %s versión %d.\n",
                    c.getCatalog(), c.getMetaData().getDatabaseProductName(),
                    c.getMetaData().getDatabaseMajorVersion());
            for (int i = 0; i < sentencias.length; i++) {
                s.execute(sentencias[i]);
                System.out.printf("La sentencia %s se ha ejecutado con exito.\n", sentencias[i]);
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
