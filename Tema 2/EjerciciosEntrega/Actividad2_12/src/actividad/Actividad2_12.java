package actividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Actividad2_12 {

    public static void main(String[] args) {
        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            /* PROBAR METODOS DE INGRESO Y RETIRADA
            String[] cuentas = {"ES1234567890123456789012", "ES9812345678901234567890", "GB92BARC2000527584985521", "US1234567890123456789012"};

            Cuenta cuenta = Cuenta.getDB(c, "ES1234567890123456789012");
            System.out.println(cuenta.toString());

            if (cuenta.ingreso(c, 123.11)) {
                System.out.println("Ingreso realizado");
            } else {
                System.out.println("Ingreso fallido");
            }

            if (cuenta.retirada(c, 123.11)) {
                System.out.println("Retirada realizada");
            } else {
                System.out.println("Retirada fallida");
            }*/

            cuentas.add(Cuenta.getDB(c, "ES1234567890123456789012"));
            cuentas.add(Cuenta.getDB(c, "ES9812345678901234567890"));
            cuentas.add(Cuenta.getDB(c, "GB92BARC2000527584985521"));
            cuentas.add(Cuenta.getDB(c, "US1234567890123456789012"));

            int transferenciasIntentadas = 0;
            int transferenciasExitosas = 0;

            Random r = new Random();

            for (int i = 0; i < 5000; i++) {
                Cuenta cuentaOrigen = cuentas.get(r.nextInt(cuentas.size()));
                Cuenta cuentaDestino = cuentas.get(r.nextInt(cuentas.size()));

                double cantidad = (double) (r.nextInt(100001)) / 100.0;

                boolean exitoOrigen = cuentaOrigen.transferenciaHacia(c, cuentaDestino, cantidad);
                if (exitoOrigen) {
                    boolean exitoDestino = cuentaDestino.transferenciaHacia(c, cuentaOrigen, cantidad);
                    if (exitoDestino) {
                        transferenciasExitosas++;
                    }
                }
                transferenciasIntentadas++;
            }

            System.out.println("Número de transferencias intentadas: " + transferenciasIntentadas);
            System.out.println("Número de transferencias exitosas: " + transferenciasExitosas);

            for (Cuenta cuenta : cuentas) {
                Cuenta cuentaActual = Cuenta.getDB(c, cuenta.getNumeroCuenta());
                System.out.println("Cuenta: " + cuentaActual.getNumeroCuenta());
                System.out.println("Moneda: " + cuentaActual.getMoneda());
                System.out.println("Saldo final: " + cuentaActual.getSaldoInicial());
            }

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
