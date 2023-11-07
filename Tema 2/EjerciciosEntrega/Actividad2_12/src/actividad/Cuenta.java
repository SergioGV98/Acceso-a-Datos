package actividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cuenta {

    private String numeroCuenta;
    private String moneda;
    private double saldoInicial;

    public Cuenta(String numeroCuenta, String moneda, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldoInicial = saldoInicial;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numeroCuenta=" + numeroCuenta + ", moneda=" + moneda + ", saldoInicial=" + saldoInicial + '}';
    }

    public boolean insertDB(Connection c) throws SQLException {

        try (PreparedStatement sInsert = c.prepareStatement("insert into cuentas (numero_cuenta, moneda, saldo_inicial) values (?, ?, ?)")) {
            int i = 1;
            sInsert.setString(i++, numeroCuenta);
            sInsert.setString(i++, moneda);
            sInsert.setDouble(i++, saldoInicial);
            sInsert.executeUpdate();
        }

        return true;
    }

    public static Cuenta getDB(Connection c, String numCuenta) throws SQLException {

        try (Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery("select numero_cuenta, moneda, saldo_inicial from cuentas where numero_cuenta = '" + numCuenta + "';");
            while (rs.next()) {
                if (rs.getString("numero_cuenta").equals(numCuenta)) {
                    return new Cuenta(rs.getString("numero_cuenta"), rs.getString("moneda"), rs.getDouble("saldo_inicial"));
                }
            }
            return null;
        }

    }

    public boolean ingreso(Connection c, double cant) throws SQLException { // Cant tiene que se mayor comprobarlo
        try (PreparedStatement sUpdate = c.prepareStatement("update cuentas set saldo_inicial = saldo_inicial + ? where numero_cuenta = ?")) {
            sUpdate.setDouble(1, cant);
            sUpdate.setString(2, this.numeroCuenta);
            int rowCount = sUpdate.executeUpdate();
            return rowCount > 0;
        }
    }

    public boolean retirada(Connection c, double cant) throws SQLException { // Cantidad no puede ser mayor comprobarlo
        try (PreparedStatement sUpdate = c.prepareStatement("update cuentas set saldo_inicial = saldo_inicial - ? where numero_cuenta = ?")) {
            sUpdate.setDouble(1, cant);
            sUpdate.setString(2, this.numeroCuenta);
            int rowCount = sUpdate.executeUpdate();
            return rowCount > 0;
        }
    }

    public boolean transferenciaHacia(Connection c, Cuenta cuentaDest, double cant) {
        try (PreparedStatement p = c.prepareStatement("UPDATE cuentas SET saldo_inicial = saldo_inicial + ? WHERE numero_cuenta = ?")) {
            c.setAutoCommit(false);

            if (this.getSaldoInicial() >= cant) {
                this.setSaldoInicial(this.getSaldoInicial() - cant);
                cuentaDest.setSaldoInicial(cuentaDest.getSaldoInicial() + cant);

                p.setDouble(1, cuentaDest.getSaldoInicial());
                p.setString(2, cuentaDest.getNumeroCuenta());
                p.executeUpdate();

                // Realizar el commit explícito
                c.commit();
                return true;
            }
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());

            try {
                c.rollback();
                System.out.println("Se hace ROLLBACK");
            } catch (SQLException exr) {
                System.out.printf("ERROR en rollback: %s.\n", exr.getMessage());
            }
        }

        return false;
    }

    public boolean transferenciaDesde(Connection c, Cuenta cuentaOrig, double cant) {
        try (PreparedStatement p = c.prepareStatement("UPDATE cuentas SET saldo_inicial = saldo_inicial - ? WHERE numero_cuenta = ?")) {
            c.setAutoCommit(false);

            if (cuentaOrig.getSaldoInicial() >= cant) {
                cuentaOrig.setSaldoInicial(cuentaOrig.getSaldoInicial() - cant);

                p.setDouble(1, cant); // Restar la cantidad al saldo_inicial
                p.setString(2, cuentaOrig.getNumeroCuenta());
                p.executeUpdate();

                // Realizar el commit explícito
                c.commit();
                return true;
            }
        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());

            try {
                c.rollback();
                System.out.println("Se hace ROLLBACK");
            } catch (SQLException exr) {
                System.out.printf("ERROR en rollback: %s.\n", exr.getMessage());
            }
        }

        return false;
    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
