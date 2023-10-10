package fichaccesoaleatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Par;

public class DrvFichAccesoAleatorio {

    /**
     * Añadir a la clase FicheroAccesoAleatorio un método que permita cambiar el
     * valor de un campo de un registro. Para identificar el registro se
     * proporcionará su posición, de la misma manera que se hace con el método
     * insertar. Es decir, el primer registro tiene posición 0. Al método se le
     * pasará también el nombre del campo y el nuevo valor para el campo.
     */
    public static void main(String[] args) {

        List campos = new ArrayList();
        campos.add(new Par("cod_cliente", 10));
        campos.add(new Par("dni", 9));
        campos.add(new Par("nom_cliente", 60));
        try {
            FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("clientes.dat", campos);
            Map reg = new HashMap();
            reg.put("cod_cliente", "100");
            reg.put("dni", "01234567Z");
            reg.put("nom_cliente", "ZARRA");
            faa.insertar(reg);
            reg.clear();
            reg.put("cod_cliente", "101");
            reg.put("dni", "23456789B");
            reg.put("nom_cliente", "BENÍTEZ");
            faa.insertar(reg);
            reg.clear();
            reg.put("cod_cliente", "102");
            reg.put("dni", "34567890C");
            reg.put("nom_cliente", "CERVERA");
            faa.insertar(reg);
            reg.clear();
            reg.put("cod_cliente", "103");
            reg.put("dni", "12345678A");
            reg.put("nom_cliente", "ASTORGA");
            faa.insertar(reg, 1);
            
            faa.cambiarRegistro(reg, 0, "cod_cliente", "109");
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}
