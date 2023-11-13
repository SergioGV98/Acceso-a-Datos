package actividad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2_16 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            int salida = transaccion(c, "FR1234567890123456789012", "ES1234567890123456789012", 10);
            if (salida == 0) {
                System.out.println("Saldo insuficiente.");
            } else if (salida == -1) {
                System.out.println("Cuenta destino no existe.");
            } else if (salida == -2) {
                System.out.println("Cuenta origen no existe.");
            } else {
                System.out.println("Transacción realizada con éxito.");
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

    public static int transaccion(Connection c, String cuentaOrigen, String cuentaDestino, double dinero) throws SQLException {
        
        /*
        DELIMITER //
        CREATE PROCEDURE transaccion(
          IN dinero DOUBLE,
          IN num_cuenta_origen VARCHAR(155),
          IN num_cuenta_destino VARCHAR(155),
          OUT resultado INT
        )
        sp: BEGIN
          DECLARE saldo_origen DOUBLE;
          DECLARE saldo_destino DOUBLE;

          SELECT saldo_inicial INTO saldo_origen FROM cuentas WHERE numero_cuenta = num_cuenta_origen;
          IF saldo_origen IS NULL THEN
            SET resultado = -2; 
            LEAVE sp;
          END IF;

          SELECT saldo_inicial INTO saldo_destino FROM cuentas WHERE numero_cuenta = num_cuenta_destino;
          IF saldo_destino IS NULL THEN
            SET resultado = -1; 
            LEAVE sp;
          END IF;

          IF saldo_origen >= dinero THEN
            START TRANSACTION;

            UPDATE cuentas SET saldo_inicial = saldo_inicial - dinero WHERE numero_cuenta = num_cuenta_origen;
            UPDATE cuentas SET saldo_inicial = saldo_inicial + dinero WHERE numero_cuenta = num_cuenta_destino;

            COMMIT;
            SET resultado = 1;
          ELSE
            ROLLBACK;
            SET resultado = 0; 
          END IF;

        END //transaccion
        DELIMITER ;
        */
        try (CallableStatement s = c.prepareCall("{call transaccion(?, ?, ?, ?)}")) {
            int i = 1;
            s.setDouble(i++, dinero);
            s.setString(i++, cuentaOrigen);
            s.setString(i++, cuentaDestino);
            s.registerOutParameter(i, java.sql.Types.INTEGER);
            s.execute();

            return s.getInt(4);
        }
    }

}
