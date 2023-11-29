package org.example;

import org.example.orm.Empleado;
import org.example.orm.Sede;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class _3_ {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Debe proporcionar dos identificadores: IDEmpleado IDSede");
            return;
        }
        String dniEmpleado = args[0];
        int idSede = Integer.parseInt(args[1]);

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Empleado empleado = session.get(Empleado.class, dniEmpleado);
            if (empleado != null) {
                System.out.printf("Empleado: %s\n", empleado);
            } else {
                System.out.println("No se encontró empleado con el ID proporcionado.");
            }

            Sede sede = session.get(Sede.class, idSede);
            if (sede != null) {
                System.out.printf("Sede: %s\n", sede);
            } else {
                System.out.println("No se encontró sede con el ID proporcionado.");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
