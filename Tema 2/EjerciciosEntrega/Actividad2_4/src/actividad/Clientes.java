package actividad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Clientes {

    private int cod_cliente;
    private String dni;
    private String nom_cliente;

    public Clientes(int cod_cliente, String dni, String nom_cliente) {
        this.cod_cliente = cod_cliente;
        this.dni = dni;
        this.nom_cliente = nom_cliente;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public String getDni() {
        return dni;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.cod_cliente;
        hash = 73 * hash + Objects.hashCode(this.dni);
        hash = 73 * hash + Objects.hashCode(this.nom_cliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clientes other = (Clientes) obj;
        if (this.cod_cliente != other.cod_cliente) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return Objects.equals(this.nom_cliente, other.nom_cliente);

    }

    @Override
    public String toString() {
        return "Cliente{" + "cod_cliente=" + cod_cliente + ", dni=" + dni + ", nom_cliente=" + nom_cliente + '}';
    }

    public static Clientes getDB(String dni, Connection c) throws SQLException {
        try (Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery("select cod_cliente, dni, nom_cliente from clientes where dni = '" + dni + "';");
            while (rs.next()) {
                if (rs.getString("dni").equals(dni)) {
                    return new Clientes(rs.getInt("cod_cliente"), rs.getString("dni"), rs.getString("nom_cliente"));
                }
            }
            return null;
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }
}
