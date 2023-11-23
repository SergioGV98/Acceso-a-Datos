package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.orm.Empleado;

public class Ejercicio4 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Debe proporcionar dos argumentos: IDEmpleado NuevoNombre");
            return;
        }

        String dniEmpleado = args[0];
        String nuevoNombre = args[1];

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Recuperar empleado por ID
                Empleado empleado = session.get(Empleado.class, dniEmpleado);

                if (empleado != null) {
                    // Cambiar el nombre del empleado
                    empleado.setNomEmp(nuevoNombre);
                    session.update(empleado);

                    System.out.println("Nombre del empleado actualizado con éxito.");

                    transaction.commit();
                } else {
                    System.out.println("No se encontró empleado con el ID proporcionado.");
                }

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
