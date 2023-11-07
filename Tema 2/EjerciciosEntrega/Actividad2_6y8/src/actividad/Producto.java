package actividad;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Producto {

    private int cod_prod;
    private String nom_prod;
    private double pr_unit;
    private String descr;

    public Producto(int cod_prod, String nom_prod, double pr_unit, String descr) {
        this.cod_prod = cod_prod;
        this.nom_prod = nom_prod;
        this.pr_unit = pr_unit;
        this.descr = descr;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public double getPr_unit() {
        return pr_unit;
    }

    public void setPr_unit(double pr_unit) {
        this.pr_unit = pr_unit;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "Productos{" + "cod_prod=" + cod_prod + ", nom_prod=" + nom_prod + ", pr_unit=" + pr_unit + ", descr=" + descr + '}';
    }

    public boolean insertDB(Connection c) throws SQLException {

        try (PreparedStatement ps = c.prepareStatement("INSERT INTO productos (cod_prod, nom_prod, pr_unit, descr) VALUES (?,?,?,?);")) {

            ps.setInt(1, cod_prod);
            ps.setString(2, nom_prod);
            ps.setDouble(3, pr_unit);
            ps.setString(4, descr);

            int row = ps.executeUpdate();
            if (row >= 1) {
                System.out.printf("Columnas afectadas: %d\n", row);
                return true;
            }
            return false;
        }
    }

    public static Producto getDB(Connection c, int cod_prod) throws SQLException {
        String selectQuery = "SELECT cod_prod, nom_prod, pr_unit, descr FROM productos WHERE cod_prod = ?";

        try (PreparedStatement preparedStatement = c.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, cod_prod);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Producto(rs.getInt("cod_prod"), rs.getString("nom_prod"), rs.getDouble("pr_unit"), rs.getString("descr"));
            } else {
                return null;
            }
        }
    }

}
