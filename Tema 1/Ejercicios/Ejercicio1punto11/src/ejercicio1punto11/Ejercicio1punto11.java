package ejercicio1punto11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
En realidad, lo que hace newLine es, básicamente, escribir un salto de línea, '\n'. Prueba a modificar el programa
anterior para que utilice solo la clase FileWriter.
 */
public class Ejercicio1punto11 {

    private static final String NOM_FICH_SALIDA = "fichero.txt";

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter(NOM_FICH_SALIDA)) {
            for (int i = 0; i < 10; i++) {
                fw.write("*".repeat(i)+"\n");
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
