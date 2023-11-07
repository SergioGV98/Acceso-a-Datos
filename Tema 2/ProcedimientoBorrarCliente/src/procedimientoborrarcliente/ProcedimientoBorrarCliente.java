package procedimientoborrarcliente;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProcedimientoBorrarCliente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String basedatos = "pruebasprog";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            /*System.out.println("Dime el codigo del cliente que quieres borrar.");
            int numero = Integer.parseInt(sc.nextLine());
            borrarCliente(c, numero);*/
 /*
            System.out.println("Dime el codigo del cliente del cual quieres obtener todas sus facturas.");
            int numero = Integer.parseInt(sc.nextLine());
            System.out.printf("Facturas del cliente %d: %d\n", numero, numeroFacturas(c, numero)); */
            transaccion(c, "ES1234567890123456789012", "ES9812345678901234567890", 100);

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

    public static void borrarCliente(Connection c, int cod_cliente) throws SQLException {
        // BORRAR UN CLIENTE
        /*
            DELIMITER //
            CREATE PROCEDURE borrar_cliente (codigo INTEGER)
            BEGIN1
                delete from clientes where cod_cliente = codigo;
            END //
            DELIMITER ;
         */
        CallableStatement s = c.prepareCall("{call borrar_cliente(?)}");
        s.setInt(1, cod_cliente);
        s.execute();
    }

    public static int numeroFacturas(Connection c, int cod_cliente) throws SQLException {
        /*
        DELIMITER //
        CREATE FUNCTION numero_facturas (numero INTEGER)
        RETURNS INTEGER DETERMINISTIC
        BEGIN
            DECLARE num_fact INTEGER;
            SELECT COUNT(num_factura) into num_fact FROM facturas where cod_cliente = numero;
            RETURN(num_fact);
        END //
        DELIMITER ;
         */
        PreparedStatement ps = c.prepareStatement("select numero_facturas(?)");
        ps.setInt(1, cod_cliente);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
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
