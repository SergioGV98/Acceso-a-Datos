package muestraficherocsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MuestraFicheroCSV {

    public static void main(String[] args) {

        try(var fr = new FileReader("prueba.txt")){
            int unCar;
            while((unCar = fr.read()) != -1){
                
            }
        } catch (IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
        
    }

}
