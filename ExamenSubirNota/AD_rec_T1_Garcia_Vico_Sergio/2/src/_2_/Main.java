package _2_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
         if(args.length < 2){
            System.out.println("Faltan argumentos.");
            return;
        } 
         
         try(var br = new BufferedReader(new FileReader(args[0])); var bw = new BufferedWriter(new FileWriter(args[1]))){ 
            String linea;
            while ((linea = br.readLine()) != null) {     
                String [] datosLinea = linea.split(",");
                for(byte i = 0; i < datosLinea.length; i++){
                    String lineaFormateada = datosLinea[i].strip();
                    if(!lineaFormateada.startsWith("\"")){
                        lineaFormateada = datosLinea[i].replaceAll(datosLinea[i], "\"" + datosLinea[i].strip() + "\",");
                    } else{
                        lineaFormateada = datosLinea[i].strip() + ",";
                    }
                    bw.write(lineaFormateada);
                }
                bw.write("\n");
            }
        } catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
         
        
    }
    
}
