package ficheroobjetos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LecturaObjetos {

    public static void main(String[] args) {
        try (var fis = new FileInputStream("./personas.txt"); var ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                System.out.printf("Persona: %s\n", ois.readObject());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
    }
}
