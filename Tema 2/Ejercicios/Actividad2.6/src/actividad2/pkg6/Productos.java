package actividad2.pkg6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Objects;

public class Productos {

    private int cod_prod;
    private String nom_prod;
    private double pr_unit;
    private String descr;

    public Productos(int cod_prod, String nom_prod, double pr_unit, String descr) {
        this.cod_prod = cod_prod;
        this.nom_prod = nom_prod;
        this.pr_unit = pr_unit;
        this.descr = descr;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public double getPr_unit() {
        return pr_unit;
    }

    public String getDescr() {
        return descr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.cod_prod;
        hash = 59 * hash + Objects.hashCode(this.nom_prod);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.pr_unit) ^ (Double.doubleToLongBits(this.pr_unit) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.descr);
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
        final Productos other = (Productos) obj;
        if (this.cod_prod != other.cod_prod) {
            return false;
        }
        if (Double.doubleToLongBits(this.pr_unit) != Double.doubleToLongBits(other.pr_unit)) {
            return false;
        }
        if (!Objects.equals(this.nom_prod, other.nom_prod)) {
            return false;
        }
        return Objects.equals(this.descr, other.descr);
    }

    @Override
    public String toString() {
        return "Productos{" + "cod_prod=" + cod_prod + ", nom_prod=" + nom_prod + ", pr_unit=" + pr_unit + ", descr=" + descr + '}';
    }

    public boolean insertDB(Connection c) throws SQLException {

        try (PreparedStatement ps = c.prepareStatement("INSERT INTO 'pruebasprog'.'productos' ('cod_prod', 'nom_prod', 'pr_unit', 'descr') VALUES (?, ?, ?, ?);")) {

            
            ps.setInt(1, 1);
            ps.setString(2, "HOLA");
            ps.setDouble(3, 2);
            ps.setString(4, "HOLA");
            
            int row = ps.executeUpdate();
            if(row >= 1){
                 System.out.printf("Columnas afectadas: %d\n", row);
                return true;
            }
            return false;
        }

    }
}
