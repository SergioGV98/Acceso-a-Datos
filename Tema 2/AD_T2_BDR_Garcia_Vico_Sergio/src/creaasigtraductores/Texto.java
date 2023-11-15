package creaasigtraductores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

public class Texto {

    private int idTexto;
    private int numPalabras;
    private String codIdioma;

    public Texto(int numPalabras, String codIdioma) {
        this.numPalabras = numPalabras;
        this.codIdioma = codIdioma;
    }

    public int getIdTexto() {
        return idTexto;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public String getCodIdioma() {
        return codIdioma;
    }

    public int saveDB(Connection c) throws SQLException {

        try (PreparedStatement ps = c.prepareStatement("INSERT INTO Texto (num_palabras, cod_idioma) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            ps.setInt(i++, numPalabras);
            ps.setString(i++, codIdioma);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            try (Statement s = c.createStatement()) {
                rs = s.executeQuery("select id_texto from texto where id_texto=last_insert_id();");
                while (rs.next()) {
                    return rs.getInt("id_texto");
                }
            }
        }
        return -1;
    }

    public static Texto getDB(Connection c, int id_texto) throws SQLException {

        try (PreparedStatement ps = c.prepareStatement("SELECT num_palabras, cod_idioma FROM texto WHERE id_texto = ?")) {
            ps.setInt(1, id_texto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return new Texto(rs.getInt("num_palabras"), rs.getString("cod_idioma"));
                }
            }
        }
        return null;
    }
    
    public void asignaTraductores (HashMap traductores){
       
    }

}
