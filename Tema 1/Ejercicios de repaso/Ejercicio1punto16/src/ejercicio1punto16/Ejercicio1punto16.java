package ejercicio1punto16;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Ejercicio1punto16 {

    /*
    Las clases ZipInputStream y ZipOutputStream permiten, respectivamente, leer y crear ficheros
    comprimidos de tipo zip. Consulta la página https://www.thecoderscorner.com/team-blog/java-and-jvm/12-reading-azip-file-from-java-using-zipinputstream/ y las alcanzables a partir de los enlaces que contiene para ver ejemplos de
    generación de ficheros zip y de extracción de sus contenidos.
    Crea un programa al que se le pase como parámetro de línea de comandos la ruta de un directorio, y que cree en el
    directorio de ejecución del programa un fichero zip con el mismo nombre que el directorio y terminado en .zip. Si
    no se le pasa una ruta de directorio que corresponda a un directorio que exista, el programa debe mostrar un mensaje
    de error y terminar su ejecución. El programa debe ser consistente con el estilo de programación utilizado en general
    hasta ahora. Por ejemplo: en lugar de utilizar un Logger, debe escribir en la salida estándar y/o de error. En la
    medida de lo posible, para trabajar con ficheros, debe utilizar la clase java.io.File y no clases del paquete
    java.nio.file.
    Crea un programa al que se le pase como parámetro de línea de comandos la ruta de un fichero zip y que lo
    descomprima en el directorio de ejecución del programa el fichero zip. El fichero podría ser uno generado por el programa anterior.
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Por favor, proporciona la ruta del directorio como argumento.");
            System.exit(0);
        }

        try (var fos = new FileOutputStream(args[0]); var zipOut = new ZipOutputStream(fos)) {
            // Creo los ficheros
            agregarArchivoAlZIP("prueba.txt", "HOLA", zipOut);
            agregarArchivoAlZIP("prueba2.txt", "Hoy es lunes", zipOut);

            // Extraigo los ficheros
            try (var fis = new FileInputStream(args[0]); var zipIn = new ZipInputStream(fis)) {
                ZipEntry entry;
                while ((entry = zipIn.getNextEntry()) != null) {
                    String fileInfo = entry.getName();
                    File directorio = new File(fileInfo);
                    try (var fos2 = new FileOutputStream(directorio)) {
                        byte[] buffer = new byte[1024];
                        int len;

                        while ((len = zipIn.read(buffer)) > 0) {
                            fos2.write(buffer, 0, len);
                        }
                        System.out.println("Archivo extraido: " + fileInfo);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
    }

    // Método para agregar un archivo al archivo ZIP
    private static void agregarArchivoAlZIP(String nombreArchivo, String contenido, ZipOutputStream zipOut) throws IOException {
        // Creo el nombre del archivo que voy a introducir
        ZipEntry zipEntry = new ZipEntry(nombreArchivo);
        // Escribo sobre el nombre del fichero
        zipOut.putNextEntry(zipEntry);

        // Guardo el contenido en un array de bytes
        byte[] bytes = contenido.getBytes();
        // Escribo byte en el archivo, empiezo en la posicion 0 y llego hasta la longitud de bytes
        zipOut.write(bytes, 0, bytes.length);

        // Ciero la entrada
        zipOut.closeEntry();
    }
}
