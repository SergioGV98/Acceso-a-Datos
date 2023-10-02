package ejercicio1punto7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1punto7 {

    /*
    Crea y ejecuta el programa anterior que escribe el texto carácter a carácter en un fichero. Después crea y ejecuta el
    segundo programa. Para que este último funcione, copia antes en su directorio de trabajo el fichero generado por el
    primer programa. Verifica que el segundo programa escribe el texto correctamente, también las vocales acentuadas y la letra ñ.
     */
    public static void main(String[] args) {

        String cadena = "Hola, soy una espléndida y muy reseñable secuencia de bytes.";
        try (FileWriter fw = new FileWriter("prueba.txt")) {
            for (int i = 0; i < cadena.length(); i++) {
                fw.write(cadena.charAt(i));
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: escribiendo a fichero: %s\n", ex.getMessage());
        }
        
        try (FileReader fr = new FileReader("prueba.txt")) {
            int unCar;
            while ((unCar = fr.read()) != -1) {
                System.out.printf("%c\n", (char) unCar);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: leyendo de fichero: %s\n", ex.getMessage());
        }
    }

}
