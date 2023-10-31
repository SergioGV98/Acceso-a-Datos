package correcionexamencifrado;

import java.io.IOException;

public class CorrecionExamenCifrado {

    public static void main(String[] args) {
        char [] orig = {' ', 'T', 'c', 'e', 'o', 'r', 's', 't', 'x'};
        char [] destino = {'N', 'L', 'T', 'Q', 'R', 'K', 'y', 'a', 'n'};
        Cifrador cif = new Cifrador(orig, destino);
        try {
            cif.cifrar("texto.txt", "textocif.txt");
            cif.descifrar("textocif.txt", "textodescifrado.txt");
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
    }
    
}
