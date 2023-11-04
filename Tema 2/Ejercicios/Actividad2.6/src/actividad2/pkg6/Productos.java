package actividad2.pkg6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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

    public Productos() {

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

    public int insertCSVDB(Connection c, String csv) throws FileNotFoundException, IOException, SQLException {

        try (var fr = new FileReader(csv); var br = new BufferedReader(fr)) {

            String linea;
            int productos = 0;

            while ((linea = br.readLine()) != null) {
                try (PreparedStatement ps = c.prepareStatement("INSERT INTO productos (cod_prod, nom_prod, pr_unit, descr) VALUES (?,?,?,?);")) {
                    String[] campos = linea.split("\\|");
                    
                    int i = 1;
                    // Este valor siempre debe existir
                    ps.setInt(i++, Integer.parseInt(campos[0]));
                    
                    // Compruebo si campo 1 (nombre) es presente en la linea y no esta vacio, si es asi lo mando a la base de datos, si no mando una cadena vacia.
                    if (campos.length >= 2 && !campos[1].isEmpty()){
                         ps.setString(i++, campos[1]);
                    } else {
                         ps.setString(i++, "");
                    }
                    
                    if (campos.length >= 3 && !campos[2].isEmpty()) {
                    ps.setDouble(i++, Double.parseDouble(campos[2]));
                    } else {
                        ps.setDouble(i++, 0.0);
                    }
                    
                    if (campos.length >= 4) {
                        ps.setString(i++, campos[3]);
                    } else {
                        ps.setString(i++, "");
                    }
                    productos += ps.executeUpdate();
                }
            }
            return productos;
        }

    }

}
