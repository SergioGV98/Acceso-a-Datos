package bufferedreader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class BufferedReader {

    public static void main(String[] args) {

       String txt = "fichero.txt";
       
       try(FileWriter f = new FileWriter(txt); BufferedWriter b = new BufferedWriter(f)){
           for(byte i = 0; i < 10; i++){
               //b.write("*".repeat(i));
               f.write("*".repeat(i)+"\n");
           }
       } catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
       }

    }
}
