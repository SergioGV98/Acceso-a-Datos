package actividad2.pkg4;

import static actividad2.pkg4.Cliente.getDB;
import static actividad2.pkg4.Cliente.muestraErrorSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2Punto4 {

    public static void main(String[] args) {
         String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
           String [] dnis = { "12345678G", "87654321E", "12345678A", "23456789B" };

           for(String unDni : dnis){
               Cliente c1 = Cliente.getDB(unDni, c);
               
               if(c1 != null){
                   System.out.printf("Datos de cliente con DNI %s: %s\n", unDni, c1);
               } else {
                    System.out.printf("No existe cliente DNI %s\n", unDni);
               }
           }
           int numeroClientes = Cliente.getNumClienteDB(c);
           
            System.out.println(numeroClientes);
            
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }
    }
    
}
