package actividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2_6 {

    public static void main(String[] args) {
        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        Producto[] productos = {new Producto(1, "SACACORCHOS", 6.50, "SACACORCHOS ACERO"),
            new Producto(2, "LAPIZ", 0.20, "LAPIZ DE MADERA"),
            new Producto(3, "BOLI", 0.40, null),
            new Producto(4, "GOMA", 6.50, "GOMA DE BORRAR GRANDE")};
        
         try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {

            for (int i = 0; i < productos.length; i++) {
                if (productos[i].insertDB(c)) {
                    System.out.println("Se ha podido insertar el producto a la base de datos.");
                } else {
                    System.out.println("No se ha podido introducir el producto en la base de datos.");
                }
            }
            
            Producto pr = new Producto(5, "SACACORCHOS", 6.50, "SACACORCHOS ACERO");
            pr.insertDB(c);
            
            Producto prVerif = Producto.getDB(c, pr.getCod_prod());
            if(!pr.equals(prVerif)){
                System.out.printf("ERROR: producto en memoria (%s) distinto del de base de datos (%s)\n", pr, prVerif);
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
