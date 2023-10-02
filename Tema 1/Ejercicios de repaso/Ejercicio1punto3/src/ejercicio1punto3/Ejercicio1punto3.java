package ejercicio1punto3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio1punto3 {

    /*
    Crea y ejecuta el primer programa. Después crea y ejecuta el segundo programa. Para que este último funcione, copia
    antes en su directorio de trabajo el fichero generado por el primer programa.
    Prueba introduciendo el el texto del primer programa vocales acentuadas y caracteres como ñ, ç, ¿, y otros que no
    existan en inglés. Verifica si el texto se ha escrito correctamente en el fichero y cómo el segundo programa muestra
    los caracteres
     */
    public static void main(String[] args) {

        /*
        String cadena = "Hola, soy una secuencia de bytes.";
        byte[] bytes = cadena.getBytes();
        try (FileOutputStream fos = new FileOutputStream("fichero.txt")) {

            for (byte unByte : bytes) {
                fos.write(unByte);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: escribiendo a fichero: %s\n", ex.getMessage());
        }*/
        try (FileInputStream fis = new FileInputStream("fichero.txt")) {
            int unByte;
            while ((unByte = fis.read()) != -1) {
                System.out.printf("%3d(%c)\n", unByte, (char) unByte);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: leyendo de fichero: %s\n", ex.getMessage());
        }

    }

}
