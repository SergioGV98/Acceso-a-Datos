package ficheroobjetos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FicheroObjetos {

    public static void main(String[] args) {

        ArrayList<Persona> arr = new ArrayList<Persona>();
        Persona[] per = {
            new Persona("Alicia", 23, "Granda"),
            new Persona("Luis", 8, "Soria"),
            new Persona("Paula", 6, "Lugo")
        };

        try (var fos = new FileOutputStream("./personas.txt"); var oos = new ObjectOutputStream(fos)) {
            for (Persona p : per) {
                oos.writeObject(p);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }

    }

}
