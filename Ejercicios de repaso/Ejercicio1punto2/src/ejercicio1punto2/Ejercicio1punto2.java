package ejercicio1punto2;

import java.io.File;
import java.io.IOException;

public class Ejercicio1punto2 {

    /*Crea un directorio, y dentro de él dos ficheros y un directorio. Para cada uno los ficheros y directorios que has creado,
    muestra su nombre, una vez obtenido mediante un método de la clase File. Para el primer directorio que has creado,
    muestra una lista con todos sus contenidos, mostrando para cada fichero y directorio su nombre y si es un fichero o un
    directorio.
    Verifica que todo se ha creado correctamente. */
    
    public static void main(String[] args) {
        
        File directory = new File("C:\\Users\\Sergio\\Desktop\\Estudios\\Acceso-a-Datos\\Ejercicios de repaso\\Ejercicio1punto2\\Ej");
        System.out.println(directory.getName());
        
        // Creacion de los directorios y archivos y monstrado nombres por consola.
        if(directory.mkdir()){
            System.out.println("Directorio ha sido creado exitosamente.");
            try {
                File directoryChild = new File("C:\\Users\\Sergio\\Desktop\\Estudios\\Acceso-a-Datos\\Ejercicios de repaso\\Ejercicio1punto2\\Ej\\Child");
                directoryChild.mkdir();
                System.out.println(directoryChild.getName());
                File f = new File(directory, "hola.txt");
                File f2 = new File(directory, "hola2.txt");
                f.createNewFile();
                System.out.println(f.getName());
                f2.createNewFile();
                System.out.println(f2.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Directorio no ha podido ser creado.");
        }
        
        //Recorrido para comprobar si es directorio o archivo
        for(File f : directory.listFiles()){
            if(f.isDirectory()){
                System.out.println(f.getName() + " es Directorio.");
            } else if (f.isFile()){
                System.out.println(f.getName() + " es un Archivo.");
            }
        }
   
        
    }
    
}
