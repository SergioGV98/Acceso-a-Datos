package repaso;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import repaso.objetos.Libro;

public class LecturaEscrituraObjetos {

    public static void main(String[] args) {

        Libro[] libros = {
            new Libro("Nacidos de la bruma: El imperio final", "Brandon Sanderson", 710),
            new Libro("Harry Potter: La orden del fenix", "J.K Rowling", 510),
            new Libro("El examen suspenso", "Sergio Garcia Vico", 310)};

        try (var fos = new FileOutputStream("./libros.csv"); var oos = new ObjectOutputStream(fos); var fis = new FileInputStream("./libros.csv"); var ois = new ObjectInputStream(fis) {
        }) {
            for (Libro l : libros) {
                oos.writeObject(l);
            }

            while (fis.available() > 0) {
                System.out.printf("Libro: %s\n", ois.readObject());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
    }

}
