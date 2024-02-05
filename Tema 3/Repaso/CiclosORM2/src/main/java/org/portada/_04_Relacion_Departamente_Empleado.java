package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Departamento;
import org.portada.modelo.Empleado;
import org.portada.modelo.Sede;

public class _04_Relacion_Departamente_Empleado {


    //Todo. Pon hibernate.hbm2ddl.auto en create
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                Sede sede1 = new Sede("Movistar");
                Sede sede2 = new Sede("EPCOS");

                s.persist(sede1);
                s.persist(sede2);

                Departamento dpto1 = new Departamento("Marketing");
                dpto1.setSede(sede1);
                s.persist(dpto1);

                Departamento dpto2 = new Departamento("Produccion");
                dpto2.setSede(sede1);
                s.persist(dpto2);

                Departamento dpto3 = new Departamento("Compras");
                dpto3.setSede(sede2);
                s.persist(dpto3);


                Empleado emp1 = new Empleado("Paco", 4321);
                emp1.setDepartamento(dpto1);

                Empleado emp2 = new Empleado("Mario", 3423);
                emp2.setDepartamento(dpto1);

                Empleado emp3 = new Empleado("Luis", 5442);
                emp3.setDepartamento(dpto2);

                s.persist(emp1);
                s.persist(emp2);
                s.persist(emp3);


                Departamento departamento1 = new Departamento();
                departamento1.setSede(sede1);

                t.commit();


                Sede findSede1 = s.find(Sede.class, 1);
                Sede findSede2 = s.find(Sede.class, 2);

                Departamento f_dpto1 = s.find(Departamento.class, 1);
                Departamento f_dpto2 = s.find(Departamento.class, 2);
                Departamento f_dpto3 = s.find(Departamento.class, 3);

                System.out.printf("Nueva sede creado con id %d: %s\n" +
                        "Con Departamentos %s\n\n", findSede1.getId(), findSede1, findSede1.getDepartamentos());
                System.out.println();

                System.out.printf("Nueva sede creado con id %d: %s\n" +
                        "Con Departamentos %s\n", findSede2.getId(), findSede2, findSede2.getDepartamentos());
                System.out.println();

                System.out.printf("El departamento con id %d\nInformación: %s\nTiene de empleado %s\n", f_dpto1.getId(), f_dpto1, f_dpto1.getEmpleados());
                System.out.println();
                System.out.printf("El departamento con id %d\nInformación: %s\nTiene de empleado %s\n", f_dpto2.getId(), f_dpto2, f_dpto2.getEmpleados());
                System.out.println();
                System.out.printf("El departamento con id %d\nInformación: %s\nTiene de empleado %s\n", f_dpto3.getId(), f_dpto3, f_dpto3.getEmpleados());

            } catch (Exception ex) {
                ex.printStackTrace();
                if (t != null) {
                    System.out.println("ERROR: ROLLBACK");
                    t.rollback();
                }
            }
        }
    }
}
