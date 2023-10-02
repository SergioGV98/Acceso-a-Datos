package ejercicio1punto8;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1punto8 {

    /*
    Existe un constructor de FileWriter con un parámetro que permite añadir contenido al final de un fichero en lugar
    de sobrescribir sus contenidos. Modifica el programa anterior para que lo use. Ejecútalo varias veces y verifica que se
    añade texto al final cada vez que se ejecuta.
     */
    public static void main(String[] args) {

        String cadena = "Hola, soy una espléndida y muy reseñable secuencia de bytes.";
        try (FileWriter fw = new FileWriter("prueba.txt", true)) {
            for (int i = 0; i < cadena.length(); i++) {
                fw.write(cadena.charAt(i));
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: escribiendo a fichero: %s\n", ex.getMessage());
        }
        
        try (FileReader fr = new FileReader("prueba.txt")) {
            int unCar;
            while ((unCar = fr.read()) != -1) {
                System.out.printf("%c", (char) unCar);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: leyendo de fichero: %s\n", ex.getMessage());
        }
        
    }

}
