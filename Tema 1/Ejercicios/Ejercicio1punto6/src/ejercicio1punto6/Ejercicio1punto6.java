package ejercicio1punto6;

import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio1punto6 {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("No se ha encontrado el nombre del archivo por argumentos.");
            System.exit(0);
        }

        String fichero = args[0];

        try (FileInputStream f = new FileInputStream(fichero)) {
            int unByte;
            byte contador = 0;
            byte posicion = 0;
            String texto = "";
            while ((unByte = f.read()) != -1) {
                
                if (contador == 0) {
                    System.out.printf("%04X | ", posicion);
                }
                
                System.out.printf("%02X ", unByte);
                contador++;
                posicion++;
                texto += (char) unByte;
                
                if (contador == 16) {
                    System.out.printf("|%s|\n", texto);
                    texto = "";
                    contador = 0;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
