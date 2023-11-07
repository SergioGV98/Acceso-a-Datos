package actividad;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actividad2_9 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {

            try {
                PreparedStatement sInsertFact = c.prepareStatement("insert into facturas (cod_cliente, fecha_factura) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);  // Me genera solo la base de datos los valores que no ponga
                PreparedStatement sInsertLinFact = c.prepareStatement("insert into lineas_facturas(num_factura, lin_factura, cod_prod, cantidad, pr_unit) values (?,?,?,?,?);");

                c.setAutoCommit(false);

                int i = 1;
                sInsertFact.setInt(i++, 3);
                sInsertFact.setDate(i++, new java.sql.Date(new java.util.Date().getTime()));
                sInsertFact.executeUpdate();
                ResultSet rs = sInsertFact.getGeneratedKeys();
                rs.next();
                int numFact = rs.getInt(1);

                System.out.printf("Creada nueva factura con numero: %d.\n", numFact);

                int lineaFact = 1;
                i = 1;

                sInsertLinFact.setInt(i++, numFact);
                sInsertLinFact.setInt(i++, lineaFact++);
                sInsertLinFact.setInt(i++, 3);
                sInsertLinFact.setDouble(i++, 24);
                sInsertLinFact.setDouble(i++, 5.84);
                sInsertLinFact.executeUpdate();

                i = 1;
                sInsertLinFact.setInt(i++, numFact);
                sInsertLinFact.setInt(i++, lineaFact++);
                sInsertLinFact.setInt(i++, 2); // Código de producto
                //sInsertLinFact.setInt(i++, 12); Código de producto para provocar fallo
                sInsertLinFact.setDouble(i++, 120); 
                sInsertLinFact.setDouble(i++, 3.15); 
                sInsertLinFact.executeUpdate();
                
                c.commit();
                
                System.out.printf("Creadas %d lineas de facturas para factura con numero %d.\n", lineaFact-1, numFact);

            } catch (Exception ex) {
                if (ex instanceof SQLException){
                    muestraErrorSQL((SQLException) ex);
                } else {
                    System.out.printf("ERROR: %s\n", ex.getMessage());
                }
                
                try{
                    c.rollback();
                    System.out.println("Se hace ROLLBACK");
                } catch(SQLException exr){
                    System.out.printf("ERROR en rollback: %s.\n", exr.getMessage());
                }
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
