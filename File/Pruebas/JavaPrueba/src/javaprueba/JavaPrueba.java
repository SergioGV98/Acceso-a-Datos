package javaprueba;

import java.io.File;
import java.io.IOException;

public class JavaPrueba {
    public static void main(String[] args) {
        
        
        // Forma 1
        /*File f;
         
        if(args.length < 1){
            System.out.print("Falta nombre en argumentos.\n");
            return;
        } else {
            String nomFich = args[0];
            f = new File(nomFich);
            try {
            f.createNewFile();
        } catch (IOException ex) {
            System.err.printf("ERROR: %s", ex.getMessage());
        }
        }*/
        
        // Forma 2
        if(args.length < 1){
            System.out.print("Falta nombre en argumentos.\n");
            return;
        }
        String nomFich = args[0];
        File f = new File(nomFich);
        
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.err.printf("ERROR: %s", ex.getMessage());
        }
        
        if(f.exists()){
            //System.out.printf("%s existe.\n", f.getName()); - Profesor le gusta esta forma
            System.out.println(f.getName() + " existe.");
        } else {
            System.out.printf("%s no existe.\n", f.getName()); 
        }
        
        //File.separator; Para separar segun el sistema con / o \
    }
    
}
