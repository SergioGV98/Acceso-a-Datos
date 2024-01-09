package repaso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CopiaDirectorioExamen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Directorio de origen");
        File dirOrig = new File(sc.nextLine());

        System.out.println("Directorio de destino");
        File dirDestino = new File(sc.nextLine());
        
        if(!dirOrig.exists()){
            System.out.println("El directorio proporcionado no existe.");
            return;
        }
        
        if(dirDestino.exists()){
            System.out.println("El directorio proporcionado no puede existir.");
            return;
        }
        
        if(dirDestino.mkdir()){
            System.out.println("El directorio se ha creado con exito.");
        } else {
            System.out.println("El directorio no se ha podido crear.");
            return;
        }
        
        int byteLeido;
        
        for(File f : dirOrig.listFiles()){
            if(f.isFile()){
                try(var fis = new FileInputStream(f); var fos = new FileOutputStream(dirDestino + File.separator + f.getName())){
                    f.createNewFile();
                    while ((byteLeido = fis.read()) != -1) {                        
                        fos.write(byteLeido);
                    }
                } catch (IOException ex) {
                    System.out.printf("ERROR: %s\n", ex.getMessage());
                }
            }
        }

    }

}
