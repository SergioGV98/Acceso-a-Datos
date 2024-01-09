package repaso.objetos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cifrador {

    char[] origen;
    char[] destino;

    public Cifrador(char[] origen, char[] destino) throws Exception {
        if (origen.length != destino.length) {
            throw new Exception("La longitud de las cadenas son distintas");
        }
        this.origen = origen;
        this.destino = destino;
    }

    public void cifrar(String inputFilePath, String outputFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath)); BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            int c;
            while ((c = br.read()) != -1) {
                char caracterActual = (char) c;
                int index = buscarIndiceCaracter(caracterActual, origen);
                char caracterCifrado = (index != -1) ? destino[index] : caracterActual;
                bw.write(caracterCifrado);
            }
        }
    }

    public void descifrar(String inputFilePath, String outputFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath)); BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            int c;
            while ((c = br.read()) != -1) {
                char caracterActual = (char) c;
                int index = buscarIndiceCaracter(caracterActual, destino);
                char caracterDescifrado = (index != -1) ? origen[index] : caracterActual;
                bw.write(caracterDescifrado);
            }
        }
    }

    private int buscarIndiceCaracter(char caracter, char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == caracter) {
                return i;
            }
        }
        return -1;
    }

}
