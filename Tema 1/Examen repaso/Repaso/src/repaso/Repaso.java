package repaso;

import clases.Empleado;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repaso {

    public static void main(String[] args) {

        /**
         * Actividad 1.1 Crea un fichero temporal utilizando el método
         * createTempFile, sin especificar el directorio donde se crea. Escribe
         * la ruta absoluta o completa para el fichero, una vez creado. Verifica
         * que el fichero está en ese directorio.
         */
        /*
        try {       
            File fich = File.createTempFile("prueba", ".txt");
            System.out.printf("Ruta del archivo temporal: %s\n", fich.getAbsoluteFile());
            if(fich.exists()){
                System.out.println("El archivo existe en la ubicacion.");
            } else {
                System.out.println("El archivo no existe en la ubiacion especificada.");
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        } */
        /**
         * Actividad 1.2 Crea un directorio, y dentro de él dos ficheros y un
         * directorio. Para cada uno los ficheros y directorios que has creado,
         * muestra su nombre, una vez obtenido mediante un método de la clase
         * File. Para el primer directorio que has creado, muestra una lista con
         * todos sus contenidos, mostrando para cada fichero y directorio su
         * nombre y si es un fichero o un directorio. Verifica que todo se ha
         * creado correctamente.
         */
        /*
        File directorio = new File("./padre");
        if (!directorio.isDirectory()) {
            directorio.mkdir();
        }

        if (directorio.exists()) {
            try {
                File archivo1 = new File(directorio,"Archivo1.txt");
                archivo1.createNewFile();
                System.out.printf("%s\n", archivo1.getName());
                File archivo2 = new File(directorio, "Archivo2.txt");
                archivo2.createNewFile();
                System.out.printf("%s\n", archivo2.getName());
                File directorioHijo = new File(directorio + "/hijo");
                directorioHijo.mkdir();
            } catch (IOException ex) {
                System.out.printf("ERROR: %s\n", ex.getMessage());
            }
        }
        
        System.out.println();
        
        for(File f : directorio.listFiles()){
            if(f.isDirectory()){
                System.out.printf("%s es un Directorio\n", f.getName());
            } else if (f.isFile()){
                System.out.printf("%s es un Archivo\n", f.getName());
            }
        }
         */
 /*
        Actividad 1.3 Crea y ejecuta el primer programa. Después crea y ejecuta el segundo programa. Para que este último funcione, copia
        antes en su directorio de trabajo el fichero generado por el primer programa.
        Prueba introduciendo el el texto del primer programa vocales acentuadas y caracteres como ñ, ç, ¿, y otros que no
        existan en inglés. Verifica si el texto se ha escrito correctamente en el fichero y cómo el segundo programa muestra
        los caracteres
         */
 /*
        String cadena = " Hola , soy una secuencia de bytes. ñ, ç, ¿";
        byte[] cadenaBytes = cadena.getBytes();
        try (var fos = new FileOutputStream("fichero.txt"); var fis = new FileInputStream("fichero.txt")) {
            for (byte unByte : cadenaBytes) {
                fos.write(unByte);
            }

            int unByte;
            byte x = 0;
            while ((unByte = fis.read()) != -1) {
                System.out.printf("%3d(%c) ", unByte, (char) unByte);
                x++;
                if (x == 5){
                    System.out.println();
                    System.out.println();
                    x = 0;
                }

            }
        } catch (IOException ex) {
            System.out.printf(" ERROR : escribiendo a fichero : % s\n ", ex.getMessage());
        } 
         */
 /*
        Actividad 1.4 
         */
 /*
        if(args.length < 1){
            System.out.println("No se encuentra el nombre de fichero por args.");
            return;
        }
        
        File archivo = new File(args[0]);
        
        if(!archivo.exists()){
            System.out.println("El fichero no existe.");
            return;
        }
        
        try(var original = new FileInputStream(archivo); var copia = new FileOutputStream("./" + archivo.getName() + ".bak")){
            
            int byteLeido;
            
            while((byteLeido = original.read()) != -1){
                copia.write(byteLeido);
            }
            
        } catch (IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
         */
        /**
         * Actividad 1.6
         */
        /*
        if(args.length < 1){
            System.out.println("No se encuentran argumentos.");
            return;
        }
        
        String fichero = args[0];
        
        try(var fis = new FileInputStream(fichero)){
            int unByte;
            byte contador = 0;
            byte posicion = 0;
            String texto = "";
            
            while((unByte = fis.read()) != -1){
                
                if (contador == 0){
                    System.out.printf("%08X | ", posicion);
                }
                
                System.out.printf("%02X ", unByte);
                texto += (char) unByte;
                contador++;
                posicion++;
                
                if (contador == 16){
                    System.out.printf("|%s|\n", texto);
                    texto = "";
                    contador = 0;
                }
            }
            
        } catch (IOException ex){
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }
         */
        Empleado[] emp = {
            new Empleado(1, "252352", "Paco", 1240.21, false),
            new Empleado(2, "2432424", "Antonio", 812.42, true),
            new Empleado(3, "432424", "Maria", 2144.11, false)
        };
        List<Empleado> empleados = new ArrayList<>();

        try (var os = new FileOutputStream("empleados.txt"); var dos = new DataOutputStream(os); var fis = new FileInputStream("empleados.txt"); var dat = new DataInputStream(fis)) {
            for (Empleado empleado : emp) {
                dos.writeInt(empleado.getNumEmp());
                dos.writeUTF(empleado.getDNI());
                dos.writeUTF(empleado.getNombre());
                dos.writeDouble(empleado.getSalBrutoAnual());
                dos.writeBoolean(empleado.istParcial());
            }

            // Lectura por consola y creacion de empleados
            while (fis.available() > 0) { // Con esto no meda fallos de codigo inalcanzable
                int numEmp = dat.readInt();
                String dni = dat.readUTF();
                String nombre = dat.readUTF();
                double salBrutoAnual = dat.readDouble();
                boolean esParcial = dat.readBoolean();

                System.out.println("-------------EMPLEADO-------------");
                System.out.printf("Número de Empleado: %d\n", numEmp);
                System.out.printf("DNI: %s\n", dni);
                System.out.printf("Nombre: %s\n", nombre);
                System.out.printf("Salario Bruto Anual: %.2f\n", salBrutoAnual);
                System.out.printf("Es Parcial: %b\n", esParcial);
                System.out.println();

                empleados.add(new Empleado(numEmp, dni, nombre, salBrutoAnual, esParcial));
            }

            for (Empleado empl : empleados) {
                System.out.println(empl);
            }

        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex.getMessage());
        }

    }

}
