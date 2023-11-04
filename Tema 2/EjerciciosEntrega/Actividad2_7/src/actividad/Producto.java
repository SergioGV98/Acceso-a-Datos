package actividad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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

    public static int insertCSVDB(Connection c, String csv) throws FileNotFoundException, IOException, SQLException {
    try (var fr = new FileReader(csv); var br = new BufferedReader(fr)) {
        String linea;
        int productos = 0;

        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split("\\|");

            int codProd = Integer.parseInt(campos[0]);
            String nomProd = campos.length >= 2 && !campos[1].isEmpty() ? campos[1] : null;
            double prUnit = campos.length >= 3 && !campos[2].isEmpty() ? Double.parseDouble(campos[2]) : 0.0;
            String descr = campos.length >= 4 ? campos[3] : null;

            Producto producto = new Producto(codProd, nomProd, prUnit, descr);
            
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO productos (cod_prod, nom_prod, pr_unit, descr) VALUES (?,?,?,?);")) {
                ps.setInt(1, producto.getCod_prod());
                ps.setString(2, producto.getNom_prod());
                ps.setDouble(3, producto.getPr_unit());
                ps.setString(4, producto.getDescr());
                
                productos += ps.executeUpdate();
            }
        }
        return productos;
    }
}

}
