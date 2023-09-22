package javaprueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        /*if(args.length < 1){
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
            }*/
        //File.separator; Para separar segun el sistema con / o \
        // Recorrer ficheros
        /*String nomDir;
            
            if(args.length >= 1){
            nomDir = args[0];
            } else {
            nomDir = ".";
            }
            
            File f = new File(nomDir);
            
            if(!f.exists()){
            System.out.println("No existe " + nomDir);
            return;
            } else if(!f.isDirectory()){
            System.out.println("No es un directorio " + nomDir);
            return;
            }
            
            File[] lFich = f.listFiles();
            
            /*for(int i = 0; i  < lFich.length; i++){
            System.out.println(lFich[i]);
            }
            
            for(File unFich: lFich){
            System.out.println(unFich.getName());
            if(unFich.isDirectory()){
            System.out.print("/");
            }
            System.out.println();
            }*/
        //Crear un fichero temporal
        /*try {
            File f = File.createTempFile("cb_", "");
            System.out.printf("Fichero temporal creado " 
                    + f.getAbsolutePath() + " con longitud " + f.length() + ".\n");
            f.delete();
        } catch (IOException ex) {
            System.out.printf("ERR: %s\n" + ex.getMessage());
        } */
        
        //Otra cosa
        if (args.length < 1) {
            System.out.println("ERROR: indicar fichero");
        }

        String nomFich = args[0];

        try {
            FileInputStream fis = new FileInputStream(nomFich);
            
            int i = 0;
            
            int unByte;
            
            while((unByte = fis.read()) != -1){
                System.out.printf("%4d - %d\n", i++, unByte);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
