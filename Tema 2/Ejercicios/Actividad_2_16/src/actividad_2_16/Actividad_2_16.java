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
            (IN in_numero_cuenta_origen VARCHAR(24), IN in_numero_cuenta_destino VARCHAR(24), IN in_dinero decimal(10,2), 
             OUT out_resultado int)
            sp: BEGIN
            declare saldo_origen decimal(10,2);
            declare cuenta_origen varchar(24);
            declare cuenta_destino varchar(24);
            SELECT saldo_inicial into saldo_origen from cuentas where numero_cuenta = in_numero_cuenta_origen;
            SELECT numero_cuenta into cuenta_origen from cuentas where numero_cuenta = in_numero_cuenta_destino;
            SELECT numero_cuenta into cuenta_destino from cuentas where numero_cuenta = in_numero_cuenta_destino;

                    select out_resultado = -1;
                    if cuenta_origen like in_numero_cuenta_origen AND cuenta_destino like in_numero_cuenta_destino then
                select out_resultado = 0;
                            if saldo_origen >= in_dinero then
                                    UPDATE cuentas SET saldo_inicial = saldo_inicial - in_dinero WHERE numero_cuenta = in_numero_cuenta_origen;
                                    UPDATE cuentas SET saldo_inicial = saldo_inicial + in_dinero WHERE numero_cuenta = in_numero_cuenta_destino;
                            end if;
                end if;
                select out_resultado = 1;
            END //
            DELIMITER ;
             */
            double dinero = 100000;

            try (CallableStatement s = c.prepareCall("{call transferencia(?, ?, ?, ?)}")) {

                int i = 1;
                
                s.setString(i++, "ES1234567890123456789012");
                s.setString(i++, "ES9812345678901234567890");
                s.setDouble(i++, dinero);
                s.registerOutParameter(i, java.sql.Types.INTEGER);
                s.execute();

                int cod_nuevo_prod = s.getInt(i);

                System.out.printf(
                        "Código asignado al nuevo producto: %s.\n", cod_nuevo_prod);

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
