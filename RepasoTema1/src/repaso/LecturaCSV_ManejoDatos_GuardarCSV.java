
package repaso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LecturaCSV_ManejoDatos_GuardarCSV {

    public static void main(String[] args) {
        
        if(args.length < 1){
            System.out.println("Faltan argumentos.");
            return;
        } else if (!new File(args[0]).exists()){
            System.out.println("La ruta que se proporciona no existe.");
            return;
        }
        
        try(var br = new BufferedReader(new FileReader(args[0])); var bw = new BufferedWriter(new FileWriter("salida.csv"))){ //True te sirve para seguir escribiendo al final del archivo
            String linea;
            String [] datos = br.readLine().split(",");
            int posicion = 1;
            while ((linea = br.readLine()) != null) {     
                String [] persona = linea.split(",");
                String dato = String.format("Linea%d => %s: %s, %s: %s, %s: %s\n", posicion++, datos[0], persona[0], datos[1], persona[1], datos[2], persona[2]);
                bw.write(dato);
                System.out.printf(dato);
            }
        } catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
    }
}
