package ejercicio1punto14;

import clases.Empleado;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Todo lo que se pide en esta actividad debe estar en un mismo proyecto. Crea
 * una clase Empleado, con atributos numEmp de tipo int, dni de tipo String,
 * nombre de tipo String, salBrutoAnual de tipo double, y tParcial de tipo
 * boolean. Crea un programa que cree tres objetos de la clase Empleado y
 * escriba sus datos en un fichero, utilizando la clase DataOutputStream. Debes
 * crear una nueva clase para ello, con un método main para que sea ejecutable.
 * Crea un programa que lea un fichero con datos de empleados, utilizando la
 * clase DataInputStream, y que a partir de ellos cree los correspondientes
 * objetos de la clase Empleado, y que los introduzca en una lista (objeto de
 * una clase cualquiera que implemente la interfaz List), y después itere sobre
 * la lista para mostrar los datos de cada empleado. Debes crear una nueva clase
 * para ello, con un método main para que sea ejecutable.
 *
 */
public class Ejercicio1punto14 {

    public static void main(String[] args) {

        /*
        Empleado[] empl = {
            new Empleado(1, "26352", "Paco", 1240.21, false),
            new Empleado(2, "154663", "Antonio", 810.11, true),
            new Empleado(3, "632421", "Maria", 2143.61, false)
        };

        try (var os = new FileOutputStream("empleados.txt"); var dos = new DataOutputStream(os)) {
            for (Empleado empleado : empl) {
                dos.writeInt(empleado.getNumEmp());
                dos.writeUTF(empleado.getDni());
                dos.writeUTF(empleado.getNombre());
                dos.writeDouble(empleado.getSalBrutoAnual());
                dos.writeBoolean(empleado.istParcial());
            }
        } catch (IOException ex) {
            System.out.printf("ERROR: %s", ex.getMessage());
        }
         */
        List<Empleado> empleados = new ArrayList<>();
        try (FileInputStream is = new FileInputStream("empleados.txt"); DataInputStream dis = new DataInputStream(is)) {
            while (true) {
                int numEmp = dis.readInt();
                String dni = dis.readUTF();
                String nombre = dis.readUTF();
                double salBrutoAnual = dis.readDouble();
                boolean tParcial = dis.readBoolean();
                empleados.add(new Empleado(numEmp, dni, nombre, salBrutoAnual, tParcial));
            }
        } catch (EOFException ex) {
            // No es un error, simplemente indica que hemos terminado de leer el archivo
        } catch (IOException ex) {
            System.out.printf("ERROR: %s", ex.getMessage());
        }

        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }
}
