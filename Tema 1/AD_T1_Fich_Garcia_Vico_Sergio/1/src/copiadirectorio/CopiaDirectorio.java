package copiadirectorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaDirectorio {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Faltan argumentos.");
            return;
        }

        File directorioExiste = new File(args[0]);
        File directorioNoExiste = new File(args[1]);

        if (!directorioExiste.exists()) {
            System.out.println("El directorio proporcionado en args 0 no existe.");
            return;
        }

        if (directorioNoExiste.exists()) {
            System.out.println("Este directorio no puede existir.");
            return;
        }

        directorioNoExiste.mkdir();
        int byteLeido;
        for (File f : directorioExiste.listFiles()) {
            if (f.isFile()) {
                try (var fis = new FileInputStream(f); var fos = new FileOutputStream(directorioNoExiste+File.separator+f.getName())) {
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
