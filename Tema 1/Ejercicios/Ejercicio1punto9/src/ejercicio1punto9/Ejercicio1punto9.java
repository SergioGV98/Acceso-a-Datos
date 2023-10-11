package ejercicio1punto9;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1punto9 {

    /*
    La clase FileWriter tiene métodos que permiten escribir un String de una vez en un fichero. Cambia el primer
    programa de ejemplo para que escriba directamente el String en el fichero.
    Cambia el segundo programa para que lea el fichero generado por el anterior programa utilizando un array de char
    con longitud 5. La longitud del array se debe definir como una constante de clase (final static). El programa
    leerá repetidas veces del fichero hacia el array. En la última lectura, cuando se llegue al final del fichero, leerá un
    número de caracteres inferior a la longitud del array, y así sabrá que ha llegado al final del fichero. La salida del
    programa será igual que la del programa anterior a partir del cual se ha creado.
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
        } catch (IOException ex) {
            System.out.printf("ERROR: leyendo de fichero: %s\n", ex.getMessage());
        }
    }

}
