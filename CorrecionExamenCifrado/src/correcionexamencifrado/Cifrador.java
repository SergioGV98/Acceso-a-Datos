
package correcionexamencifrado;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cifrador {
    
    char[] carOrigen;
    char[] carDestino;

    public Cifrador(char[] carOrigen, char[] carDestino) {
        this.carOrigen = carOrigen;
        this.carDestino = carDestino;
        
        if(carOrigen.length != carDestino.length){
            throw new IllegalArgumentException("Longitud de array de caracteres origen y destino no coinciden");
        }
    }
    
    void cifrar(String fOrigen, String fDest) throws IOException{
        try(var fr = new FileReader(fOrigen); var fw = new FileWriter(fDest)){
            int car;
            
            while((car = fr.read()) != -1) {
                char carC = (char) car;
                
                char carSalida = carC;
                
                for(int i = 0; i < carOrigen.length; i++){
                    if(carOrigen[i] == carC){
                        carSalida = carDestino[i];
                        break;
                    } 
                }
                fw.write(carSalida);
            }
        }
    }
    
     void descifrar(String fOrigen, String fDest) throws IOException{
        try(var fr = new FileReader(fOrigen); var fw = new FileWriter(fDest)){
            int car;
            
            while((car = fr.read()) != -1) {
                char carC = (char) car;
                
                char carSalida = carC;
                
                for(int i = 0; i < carOrigen.length; i++){
                    if(carDestino[i] == carC){
                        carSalida = carOrigen[i];
                        break;
                    } 
                }
                fw.write(carSalida);
            }
        }
    }

}
