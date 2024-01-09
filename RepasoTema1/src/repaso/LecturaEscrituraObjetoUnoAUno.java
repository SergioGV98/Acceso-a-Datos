
package repaso;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import repaso.objetos.Libro;

public class LecturaEscrituraObjetoUnoAUno {
    public static void main(String[] args) {
        try (var fos = new FileOutputStream("./librosPrueba.csv");
             var oos = new ObjectOutputStream(fos);
             var fis = new FileInputStream("./librosPrueba.csv");
             var ois = new ObjectInputStream(fis);
             var pw = new PrintWriter(new FileWriter("./librosPrueba.csv", true))) {

            Libro prueba = new Libro("Prueba", "Paco", 10);

            // Guardar el objeto como un objeto en el archivo CSV
            oos.writeObject(prueba);

            // Imprimir la información del objeto guardado
            System.out.printf("Libro: %s %s %d\n", prueba.getTitulo(), prueba.getAutor(), prueba.getnPaginas());

            // Leer objetos desde el archivo CSV
            while (true) {
                try {
                    Libro libro = (Libro) ois.readObject();
                    System.out.printf("Libro: %s %s %d\n", libro.getTitulo(), libro.getAutor(), libro.getnPaginas());
                } catch (EOFException e) {
                    // Se alcanzó el final del archivo, salir del bucle
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
    }
}
