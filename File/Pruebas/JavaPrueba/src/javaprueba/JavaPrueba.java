package javaprueba;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaPrueba {
    public static void main(String[] args) {
        
        File f = new File("a.txt");
        
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(JavaPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(f.exists()){
            System.out.printf("%s existe.\n", f.getName());
        } else {
            System.out.printf("%s no existe.\n", f.getName()); 
        }
        
    }
    
}
