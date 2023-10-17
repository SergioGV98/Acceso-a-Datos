package ficherotempescribirmover;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FicheroTempEscribirMover {

    public static void main(String[] args) {

        File fTemp;
        try {
            fTemp = File.createTempFile("cbf", "");
        } catch (IOException ex) {
            System.out.println("No se puede crear el fichero temporal.");;
            return;
        }

        try (var fTxt = new FileWriter(fTemp)) {
            fTxt.write("Hola.");
            System.out.printf("Creado fichero temporal %s\n", fTemp.getAbsolutePath());
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
            return;
        }

        boolean res = fTemp.renameTo(new File("salida.txt"));
        if (!res) {
            System.out.println("ERROR: No se pudo mover fichero temporal a directorio actual");
        }

        try {
            File nuevoFich = new File("salida.txt");
            Files.move(fTemp.toPath(), nuevoFich.toPath());
            System.out.printf("Movido fichero temporal %s a %s\n", fTemp.getAbsolutePath(), nuevoFich.getAbsoluteFile());
        } catch (IOException ex) {
            System.out.printf("ERROR: moviendo fichero temporal, %s\n", ex.getMessage());
        }

    }

}
