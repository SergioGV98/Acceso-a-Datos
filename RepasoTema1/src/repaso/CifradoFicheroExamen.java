package repaso;

import java.io.IOException;
import repaso.objetos.Cifrador;

public class CifradoFicheroExamen {

    public static void main(String[] args) {
        char[] arrayOriginal = {' ', 'T', 'c', 'e', 'o', 'r', 's', 't', 'x'};
        char[] arraySustituto = {'y', '+', 'e', 'c', 'q', ' ', 'p', 'm', 'w'};

        try {
             Cifrador cifrador = new Cifrador(arrayOriginal, arraySustituto);
            cifrador.cifrar("textosecreto.txt", "fichero_cifrado.txt");

            cifrador.descifrar("fichero_cifrado.txt", "fichero_descifrado.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
