package cifradofichero;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cifrador {

    char[] origen = new char[8];
    char[] destino = new char[8];

    public Cifrador(char[] origen, char[] destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public char[] getOrigen() {
        return origen;
    }

    public char[] getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Cifrador{" + "origen=" + origen + ", destino=" + destino + '}';
    }

    public void cifrado(String pathCifrar, String cifrado) throws IOException {

        if (origen.length != destino.length) {
            System.out.println("La longitud de los arrays no de igual.");
            System.exit(0);
        }
        try (var fis = new FileInputStream(pathCifrar); var fos = new FileOutputStream(cifrado)) {
            char[] arr = new char[destino.length];
            for (int i = 0; i < origen.length; i++) {
                arr[i] = destino[i];
            }
            String cadena = "";
            for (int i = 0; i < arr.length; i++) {
                cadena += arr[i];
                fos.write(cadena.charAt(i));
            }
        }
    }

    public void descifrado(String pathCifrar, String descifrado) throws IOException {
        if (origen.length != destino.length) {
            System.out.println("La longitud de los arrays no de igual.");
            System.exit(0);
        }
        try (var fis = new FileInputStream(pathCifrar); var fos = new FileOutputStream(descifrado)) {
            char[] arr = new char[origen.length];
            for (int i = 0; i < origen.length; i++) {
                arr[i] = origen[i];
            }
            String cadena = "";
            for (int i = 0; i < arr.length; i++) {
                cadena += arr[i];
                fos.write(cadena.charAt(i));
            }
        }
    }

}
