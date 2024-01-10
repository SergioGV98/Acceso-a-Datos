package _1_;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        if(args.length < 1){
            System.out.println("Falta el argumento de fichero de origen.");
            return;
        } else if (new File(args[0]).isDirectory()){
            System.out.printf("ERROR: %s es un directorio\n", args[0]);
            return;
        }
        
        if(args.length < 2){
            System.out.println("Falta el argumento de fichero de destino.");
            return;
        }
        
        int byteLeido;
        
        try(var fis = new FileInputStream(args[0]); var fos = new FileOutputStream(args[1])){
            while ((byteLeido = fis.read()) != -1) {                
                fos.write(byteLeido);
            }
            System.out.println("Copia realizada con exito.");
        }catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
        
        
    }
    
}
