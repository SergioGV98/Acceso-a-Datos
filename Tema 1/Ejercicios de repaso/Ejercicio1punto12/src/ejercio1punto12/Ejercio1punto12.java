package ejercio1punto12;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class Ejercio1punto12 {

    public static void main(String[] args) {
        
        String fichero = "prueba.txt";
        
        try(FileWriter w = new FileWriter(fichero, Charset.forName("ISO-8859-1"))){
            
            w.write("ñ y ç, y el símbolo del euro (€).");
            
        } catch (IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
        
    }
    
}
