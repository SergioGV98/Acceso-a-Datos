package ejercicio1punto15;

import clases.Empleado;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejercicio1punto15 {

    /*
    Todo lo que se pide en esta actividad debe estar en un mismo proyecto. Puedes empezar con una copia del proyecto
    desarrollado para la actividad anterior.
    Sobre la clase Empleado no se harán en principio cambios, salvo los estrictamente necesarios para que se puedan
    escribir instancias suyas utilizando la clase ObjectOutputStream.
    El proyecto debe incluir un programa que cree tres objetos de la clase Empleado y escriba sus datos en un fichero,
    utilizando la clase ObjectOutputStream. Debe haber una clase para ello, con un método main para que sea
    ejecutable.
    El proyecto debe incluir un programa que lea un fichero con objetos de la clase Empleado, utilizando la clase
    ObjectInputStream, los introduzca en una lista (objeto de una clase cualquiera que implemente la interfaz
    List), y después itere sobre la lista para mostrar los datos de cada empleado. Debe haber una nueva clase para ello,
    con un método main para que sea ejecutable.
     */
    public static void main(String[] args) {
        ArrayList<Empleado> arr = new ArrayList<Empleado>();

        Empleado[] empl = {
            new Empleado(1, "26352", "Paco", 1240.21, false),
            new Empleado(2, "154663", "Antonio", 810.11, true),
            new Empleado(3, "632421", "Maria", 2143.61, false)
        };

        try (var fos = new FileOutputStream("empleados.txt"); var oos = new ObjectOutputStream(fos); var fis = new FileInputStream("empleados.txt"); var ois = new ObjectInputStream(fis)) {
            // Escribir objetos Empleado en el archivo
            for (Empleado empleado : empl) {
                oos.writeObject(empleado);
            }

            // Leer objetos Empleado del archivo y agregarlos a la lista
            while (true) {
                Empleado empleado = (Empleado) ois.readObject();
                arr.add(empleado);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf("ERROR: %s", ex.getMessage());
        }

        for (Empleado empleado : arr) {
            System.out.println(empleado);
        }
    }

}
