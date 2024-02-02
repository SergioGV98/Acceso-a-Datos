package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Departamento;
import org.portada.modelo.Empleado;
import org.portada.modelo.Sede;

import java.util.List;

public class _04_CreaSedeDepartamentoEmpleado {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Sede sede1 = new Sede("Bosonit");
                session.persist(sede1);
                Sede sede2 = new Sede("NNT Data");
                session.persist(sede2);

                Departamento departamento1_1 = new Departamento("Android");
                departamento1_1.setSede(sede1);
                session.persist(departamento1_1);

                Departamento departamento1_2 = new Departamento("Flutter");
                departamento1_2.setSede(sede1);
                session.persist(departamento1_2);

                Departamento departamento2_1 = new Departamento("Java");
                departamento2_1.setSede(sede2);
                session.persist(departamento2_1);

                Departamento departamento2_2 = new Departamento("Python");
                departamento2_2.setSede(sede2);
                session.persist(departamento2_2);

                Empleado empleado1 = new Empleado("Sergio", 1);
                empleado1.setDepartamento(departamento1_1);
                session.persist(empleado1);

                Empleado empleado2 = new Empleado("Carlos", 2);
                empleado2.setDepartamento(departamento1_1);
                session.persist(empleado2);

                Empleado empleado3 = new Empleado("Ana", 3);
                empleado3.setDepartamento(departamento2_1);
                session.persist(empleado3);

                transaction.commit();

                displayEmployeeData(session);
                displayDepartmentsForSedes(session);

            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }

    private static void displayDepartmentsForSedes(Session session) {
        List<Sede> sedes = session.createQuery("FROM Sede", Sede.class).getResultList();
        for (Sede sede : sedes) {
            System.out.println("Sede: " + sede.getNombre());
            System.out.println("Departamentos:");

            for (Departamento departamento : sede.getDepartamentos()) {
                System.out.println("  - " + departamento.getNombre());
            }

            System.out.println();
        }
    }

    private static void displayEmployeeData(Session session) {
        List<Empleado> empleados = session.createQuery("FROM Empleado", Empleado.class).getResultList();
        System.out.println("Employee Data:");
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }
}