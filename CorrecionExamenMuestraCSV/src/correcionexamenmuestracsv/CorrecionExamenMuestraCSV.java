package correcionexamenmuestracsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CorrecionExamenMuestraCSV {

    public static void main(String[] args) {

        try (var fr = new FileReader("personas.csv"); var br = new BufferedReader(fr)) {

            String linea = "";
            int i = 1;
            String[] nomCampos = null;

            linea = br.readLine();
            if (linea == null) {
                System.out.println("Fichero CSV vacio: primera linea debe tener nombres de los campos.");
                return;
            }

            nomCampos = linea.split(",");
            
            while ((linea = br.readLine()) != null) {
                System.out.printf("Linea %d -> ", i);
                String[] valCampos = linea.split(",");
                int iCampo = 0;
                for (String valCampo : valCampos) {
                    if(iCampo > 0 ){
                        System.out.print(", ");
                    }
                    System.out.printf("%s: %s", nomCampos[iCampo++], valCampo);
                }
                System.out.println();

                i++;
            }

        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }

    }

}
