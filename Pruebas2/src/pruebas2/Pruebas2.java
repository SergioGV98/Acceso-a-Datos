package pruebas2;

import java.io.FileOutputStream;
import java.io.IOException;

public class Pruebas2 {

    public static final String NOM_FICH = "fich_binario.txt";
    
    public static void main(String[] args) {
        
        String s = "Hola.";
        
        try {
            byte [] buff = s.getBytes("UTF-8");
            try(var fos = new FileOutputStream(NOM_FICH)){
                /*for(byte b : buff){
                    fos.write(b);  //Lo mismo de de abajo
                }*/
                fos.write(buff);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
        
    }
    
}
