package repaso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FicheroTemporal {

    public static void main(String[] args) {

        try {
            File temp = File.createTempFile("prueba", ".csv", new File(".\\"));
            File path = new File(".\\");
            listDirectoriesRecursive(path, temp);
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }

    }

    private static void listDirectoriesRecursive(File directory, File temp) throws IOException {
        if (directory.isDirectory()) {
            System.out.printf("%s es un directorio\n", directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().equals("Temporal")) {
                        Files.move(temp.toPath(), file.toPath().resolve(temp.getName()));
                    }
                    try (var bw = new BufferedWriter(new FileWriter(temp, true))) {
                        bw.write(file.getName() + "\n");
                    }
                    listDirectoriesRecursive(file, temp);
                }
            }
        } else {
            System.out.printf("%s es un archivo\n", directory.getName());
        }
    }

}
