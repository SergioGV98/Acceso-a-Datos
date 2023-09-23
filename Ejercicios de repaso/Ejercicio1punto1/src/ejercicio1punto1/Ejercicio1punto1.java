package ejercicio1punto1;

import java.io.File;
import java.io.IOException;

public class Ejercicio1punto1 {

    //Crea un fichero temporal utilizando el método createTempFile, sin especificar el directorio donde se crea.
    //Escribe la ruta absoluta o completa para el fichero, una vez creado. Verifica que el fichero está en ese directorio.
   
    public static void main(String[] args) {
        
        // Crear un fichero temporal, sin especificar la ruta.
        /*
        try {
            File temp = File.createTempFile("a.txt", "");
            System.out.println("Directorio del fichero: " + temp.getAbsolutePath());
            temp.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */
        
        try {
            File directory = new File("C:\\Users\\Sergio\\Desktop\\Estudios\\Acceso-a-Datos\\Ejercicios de repaso\\Ejercicio1punto1");
            File temp = File.createTempFile("hola", ".txt", directory);
            System.out.println("Directorio del fichero: " + temp.getAbsolutePath());
            
            if(directory.exists()){
                if(temp.getParentFile().equals(directory)){
                    System.out.println("El fichero esta en el directorio correcto.");
                } else {
                    System.out.println("El fichero no esta en el directorio correcto.");
                }
                System.out.println("El directorio existe.");
            } else {
                System.out.println("El directorio no existe.");
            }
            temp.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
