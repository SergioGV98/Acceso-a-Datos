package leerutf8escribirutf16;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class LeerUTF8EscribirUTF16 {

    public static void main(String[] args) {
        String urlStr = "https://www.wikipedia.org/wiki/Luna";
        try {
            URL url = new URL(urlStr);
            int indUltBarra = urlStr.lastIndexOf('/');
            String nomFich = urlStr.substring(indUltBarra + 1);

            try (InputStream is = url.openConnection().getInputStream(); InputStreamReader isr = new InputStreamReader(is, "UTF-8"); // Nota: es por defecto UTF-8, realmente no hace falta especificarla.
                    // Nota: url.openConnection() devuelve un objeto de la clase URLConnection.
                    // Se puede obtener la codificación con el método getContentType()
                    //   de la clase URLConnection. Si la página contiene texto, 
                    //   normalmente devolverá "text/html; charset=UTF-8"
                     FileWriter fw = new FileWriter(nomFich, Charset.forName("UTF-16"))) {

                int c;
                while ((c = isr.read()) != -1) {
                    fw.write((char) c);
                }

            } catch (IOException ex) {
                System.out.printf("Error de E/S obteniendo contenidos de URL.\n");
                ex.printStackTrace();
            }
        } catch (MalformedURLException ex) {
            System.out.printf("URL mal formada: %s.\n");
            ex.printStackTrace();
        }
    }

}
