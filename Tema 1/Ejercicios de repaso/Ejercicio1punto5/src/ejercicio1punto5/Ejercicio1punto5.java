package ejercicio1punto5;

import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio1punto5 {

    private static final String NOM_FICH_ENTRADA = "fichero.txt";
    public static final int LONG_BUFF = 5;

    public static void main(String[] args) {
        
        byte[] buff = new byte[LONG_BUFF];
        
        try (FileInputStream fis = new FileInputStream(NOM_FICH_ENTRADA)) {
            int nBytesLeidos;
            while ((nBytesLeidos = fis.read(buff)) != -1) {
                for(byte b: buff){
                    System.out.printf("%3d(%c)", b, (char) b);
                }
                System.out.printf(" | %d byes leidos\n", nBytesLeidos);
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: leyendo de fichero: %s\n", ex.getMessage());
        }
    }

}
