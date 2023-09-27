package ejercicio1punto13;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejercicio1punto13 {

    public static void main(String[] args) {
        
        String url = " https://www-curator.jsc.nasa.gov/antmet/mmc/nakhla.pdf";
        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException ex) {
            System.out.printf("ERROR: leyendo la URL: %s\n", ex.getMessage());
        }
        String nomFich = url.substring(url.lastIndexOf("/") + 1);
        
        try(InputStream is = u.openConnection().getInputStream(); FileOutputStream w = new FileOutputStream(nomFich)){
            u = new URL(url);
            int byteLeido;
            while((byteLeido = is.read()) != -1){
                w.write(byteLeido);
            }
            
        } catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
