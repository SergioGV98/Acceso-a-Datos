
package actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cuenta {
    
    private String numeroCuenta;
    private String moneda;
    private double saldoInicial;

    public Cuenta(String numeroCuenta, String moneda, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldoInicial = saldoInicial;
    }
    
    public boolean insertDB(Connection c) throws SQLException{
        
        try( PreparedStatement sInsert = c.prepareStatement("insert into cuentas (numero_cuenta, moneda, saldo_inicial) values (?, ?, ?)")){
            int i = 1;
            sInsert.setString(i++, numeroCuenta);
            sInsert.setString(i++, moneda);
            sInsert.setDouble(i++, saldoInicial);
            sInsert.executeUpdate();
        }
        
        return true;
    }
    
    
}
