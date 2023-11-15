
package creatraductores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Traductor {
    
    private String dni;
    private String nombre;
    private String id_lengua;

    public Traductor(String dni, String nombre, String id_lengua) {
        this.dni = dni;
        this.nombre = nombre;
        this.id_lengua = id_lengua;
    }
    
    public boolean insertDB(Connection c) throws SQLException{
        try (PreparedStatement ps = c.prepareStatement("insert into traductor (dni_nie, nom_trad, id_lengua_nativa) values (?, ?, ?);")){
            int i = 1;
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, id_lengua);
            ps.executeUpdate();
        }
        return true;
    }

}
