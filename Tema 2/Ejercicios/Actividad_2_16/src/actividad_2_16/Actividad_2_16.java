package actividad_2_16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class Actividad_2_16 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {

            /*
            DELIMITER //
            CREATE PROCEDURE transferencia
            (IN in_numero_cuenta VARCHAR(24), IN in_dinero decimal(10,2))
            BEGIN
                UPDATE cuentas SET saldo_inicial = saldo_inicial - in_dinero WHERE numero_cuenta = in_numero_cuenta;
            END //
            DELIMITER ;
            */
            
            String numeroCuenta = "ES1234567890123456789012";
            double dinero = 120;
            
            try(CallableStatement s = c.prepareCall("{call transferencia(?, ?)}")){
                
                
                
                s.setString(1, numeroCuenta);
                s.setDouble(2, dinero);
                
                
                
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
