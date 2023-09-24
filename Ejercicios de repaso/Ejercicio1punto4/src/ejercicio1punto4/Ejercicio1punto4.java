package ejercicio1punto4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio1punto4 {


    /*
    Crea un programa al que se le pase por parámetro de línea de comandos un nombre de fichero. Este puede tener un
    path absoluto, lo que es útil para acceder a él si no está en el mismo directorio en que se ejecuta el programa. Por
    ejemplo: /etc/fstab
    Si el fichero no existe, se mostrará un mensaje de error y se terminará la ejecución del programa.
    En otro caso, se creará una copia del fichero. El nombre de la copia será igual que el del fichero original, añadiendo
    .bak al final. Esta copia se creará en el mismo directorio en el que se ejecuta el programa. No hay que hacer nada
    especial para ello. Solo asegurarse de que se toma solo el nombre del fichero, y la ruta completa, para añadirle al final
    .bak. Por ejemplo, si el fichero original que hay que copiar es /etc/fstab, entonces el nombre de la copia será
    fstab.bak.
    Para crear la copia, el programa debe crear un FileInputStream para leer del fichero de origen, byte a byte, y un
    FileOutputStream para escribir en el fichero de destino (la copia), cada byte que se va leyendo del fichero de
    origen.
     */
    
    public static void main(String[] args) {

        // Leo por args la direccion y el nombre del archivo.
        if(args.length < 1){
            System.out.println("No se ha encontrado el nombre del archivo por argumentos.");
            System.exit(0);
        }
        
        File archivo = new File(args[0]);
        
        // Si el archivo no existe lo creo y cierro la ejecucion del programa.
        if(!archivo.exists()){
            System.out.println("El fichero no existe.");
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        } else {  // Si existe uso el InputStream para guardarlo y creo una copia 
            try {
                FileInputStream original = new FileInputStream(archivo);
                FileOutputStream copia = new FileOutputStream(archivo.getAbsolutePath() + ".bak"); // cojo la direccion del archivo y le agrego su extension
                
                int byteLeido;
                
                while ((byteLeido = original.read()) != -1) { // Asigno byte a byte hasta que el archivo se quede sin byte
                    copia.write(byteLeido);
                }
                
                original.close();
                copia.close();
                
                System.out.println("Copia de seguridad creada como: " + archivo.getAbsolutePath() + ".bak");
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
    }

}
