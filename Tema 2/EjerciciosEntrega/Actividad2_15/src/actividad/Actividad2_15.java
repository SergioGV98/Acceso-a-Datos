package actividad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad2_15 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            transaccion(c, "FR1234567890123456789012", "GB92BARC2000527584985521", 1000);
        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

    public static void transaccion(Connection c, String cuentaOrigen, String cuentaDestino, double dinero) throws SQLException {
        /*
        DELIMITER //
        CREATE PROCEDURE transaccion(
          dinero DOUBLE,
          num_cuenta_origen VARCHAR(155),
          num_cuenta_destino VARCHAR(155)
        )
        BEGIN
          DECLARE saldo_origen DOUBLE;
          SELECT saldo_inicial INTO saldo_origen FROM cuentas WHERE numero_cuenta = num_cuenta_origen;

          IF saldo_origen >= dinero THEN
            START TRANSACTION;
            UPDATE cuentas SET saldo_inicial = saldo_inicial - dinero WHERE numero_cuenta = num_cuenta_origen;
            UPDATE cuentas SET saldo_inicial = saldo_inicial + dinero WHERE numero_cuenta = num_cuenta_destino;
            COMMIT;
          ELSE
            ROLLBACK;
          END IF;
        END //
        DELIMITER ;
         */
        try (CallableStatement s = c.prepareCall("{call transaccion(?, ?, ?)}")) {
            int i = 1;
            s.setDouble(i++, dinero);
            s.setString(i++, cuentaOrigen);
            s.setString(i++, cuentaDestino);
            s.execute();
        }

    }

}
