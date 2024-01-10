package _3_;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        String [] libros = {"Homero, La Iliada, griego", "Virgilio, La Eneida, lengua latina", "Brandom Sanderson, Nacidos de la bruma: El imperio final, español", "J.K. Rowling, Harry potter: La orden del fenix, ingles"};

        try {
            File fichero = new File(".\\libros.csv");
            fichero.createNewFile();
            try (var fis = new FileOutputStream(fichero); var dos = new DataOutputStream(fis)) {
                for(int i = 0; i < libros.length; i++){
                    dos.writeUTF(libros[i]);
                }
            }
            try (var is = new FileInputStream(fichero); var dis = new DataInputStream(is)) {
                while (true) {
                    try {
                        System.out.printf("Libro: %s\n", dis.readUTF());
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }

    }

}
