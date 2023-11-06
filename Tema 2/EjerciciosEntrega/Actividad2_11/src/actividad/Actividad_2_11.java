package actividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Actividad_2_11 {

    public static void main(String[] args) {

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {

            Object[][] datosCuentas = {
                {"ES9812345678901234567890", "EUR", 2354.32},
                {"GB92BARC2000527584985521", "GBP", 5634.32},
                {"ES1234567890123456789012", "EUR", 1568.12},
                {"US1234567890123456789012", "USD", 9874.90},
                {"FR1234567890123456789012", "EUR", 655.15}
            };
            c.setAutoCommit(false);

            for (Object[] datosCuenta : datosCuentas) {

                String numeroCuenta = (String) datosCuenta[0];
                String moneda = (String) datosCuenta[1];
                double saldoInicial = (double) datosCuenta[2];

                Cuenta cuenta = new Cuenta(numeroCuenta, moneda, saldoInicial);

                try {
                    if (cuenta.insertDB(c)) {
                        System.out.printf("Cuenta %s, moneda %s, saldo inicial %f creada con éxito.\n",
                                numeroCuenta, moneda, saldoInicial);
                    } else {
                        System.out.println("Error al crear la cuenta.");
                    }
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
            }
            c.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
