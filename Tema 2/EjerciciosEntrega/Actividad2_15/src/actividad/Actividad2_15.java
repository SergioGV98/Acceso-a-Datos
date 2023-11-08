package actividad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Actividad2_15 {

    public static void main(String[] args) {
        
        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;
        
    }
    
    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }
    
    public static void transaccion(Connection c, String cuentaOrigen, String cuentaDestino, double dinero) throws SQLException {
        /*
        DELIMITER //
        CREATE PROCEDURE transaccion (dinero INTEGER, num_cuenta_origen VARCHAR(155), num_cuenta_destino VARCHAR(155))
        BEGIN
            update cuentas set saldo_inicial = saldo_inicial - dinero where numero_cuenta = num_cuenta_origen;
            update cuentas set saldo_inicial = saldo_inicial + dinero where numero_cuenta = num_cuenta_destino;
        END //
        DELIMITER ;
         */
        try {
            c.setAutoCommit(false);
            CallableStatement s = c.prepareCall("{call transaccion(?, ?, ?)}");
            int i = 1;
            s.setDouble(i++, dinero);
            s.setString(i++, cuentaOrigen);
            s.setString(i++, cuentaDestino);
            s.execute();
        } catch (Exception ex) {
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
        c.commit();
    }
    
}
