package ejercicio1punto10;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1punto10 {

    /**
     * Averigua la codificación de texto con la que el programa creado para
     * ambas actividades anteriores ha generado el fichero. En Linux puedes
     * utilizar el comando file. Tanto en Linux como en Windows se puede abrir
     * el fichero con un editor de texto y seleccionar la opción “Guardar como”,
     * que normalmente muestra la codificación utilizada para el fichero y
     * permite seleccionar una distinta para guardarlo. En la documentación de
     * la clase String
     * (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/
     * String.html) se explica que la cadena de caracteres se almacena
     * internamente codificada en UTF-16. ¿Se ha cambiado la codificación en el
     * fichero con respecto a la que tenía el String en memoria? Si es así, ¿en
     * qué momento crees que puede haberse realizado esta recodificación en cada
     * programa?
     */
    final static char[] arr = new char[5];

    public static void main(String[] args) {
        String cadena = "Hola, soy una espléndida y muy reseñable secuencia de bytes.";
        try (FileWriter fw = new FileWriter("prueba.txt", true)) {
            fw.write(cadena);

        } catch (IOException ex) {
            System.out.printf("ERROR: escribiendo a fichero: %s\n", ex.getMessage());
        }

        try (FileReader fr = new FileReader("prueba.txt")) {
            int unCar;

            while ((unCar = fr.read(arr)) != -1) {
                String texto = new String(arr, 0, unCar);
                System.out.println(texto);
            }
            System.out.printf("Codificacion: %s\n", fr.getEncoding());
            System.out.println("¿Se ha cambiado la codificación en el fichero con respecto a la que tenía el String en memoria?");
            System.out.printf("Si a %s\n", fr.getEncoding());
            System.out.println(" ¿En qué momento crees que puede haberse realizado esta recodificación en cada programa?");
            System.out.println("En la creacion del archivo.");
        } catch (IOException ex) {
            System.out.printf("ERROR: leyendo de fichero: %s\n", ex.getMessage());
        }
    }

}
