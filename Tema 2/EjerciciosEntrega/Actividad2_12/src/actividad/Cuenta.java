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
    
    public boolean transferenciaHacia(Connection c, Cuenta cuentaDest, double cant){
        /*
        DELIMITER //
        CREATE PROCEDURE transaccion (dinero INTEGER, num_cuenta_origen VARCHAR(155), num_cuenta_destino VARCHAR(155))
        BEGIN
            update cuentas set saldo_inicial = saldo_inicial - dinero where numero_cuenta = num_cuenta_origen;
            update cuentas set saldo_inicial = saldo_inicial + dinero where numero_cuenta = num_cuenta_destino;
        END //
        DELIMITER ;
        */
        
        
        
        
        
        
        return false;
    }
    
    public boolean transferenciaDesde(Connection c, Cuenta cuentaOrig, double cant){
        
        
        return false;
    }

}
