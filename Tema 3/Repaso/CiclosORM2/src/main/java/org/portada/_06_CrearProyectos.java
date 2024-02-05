package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.*;

public class _06_CrearProyectos {

    //Todo. Pon hibernate.hbm2ddl.auto en update y ejecuta primero el anterior
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                Departamento dpto1 = s.find(Departamento.class, 1);
                Departamento dpto2 = s.find(Departamento.class, 2);
                Departamento dpto3 = s.find(Departamento.class, 3);

                if (dpto1 == null || dpto2 == null || dpto3 == null ) {
                    System.out.println("Uno de los departamentos no ha sido encontrado.");
                    return;
                }

                t = s.beginTransaction();

                Proyecto proy1 = new Proyecto("Edificio_2131");
                Proyecto proy2 = new Proyecto("Vivienda_028ks");
                Proyecto proy3 = new Proyecto("Universo_11x1o");

                proy1.getDepartamentos().add(dpto1);
                proy1.getDepartamentos().add(dpto2);

                proy2.getDepartamentos().add(dpto2);
                proy2.getDepartamentos().add(dpto3);

                proy3.getDepartamentos().add(dpto3);

                s.persist(proy1);
                s.persist(proy2);
                s.persist(proy3);

                t.commit();

                Proyecto fproy1 = s.find(Proyecto.class, 1);
                Proyecto fproy2 = s.find(Proyecto.class, 2);
                Proyecto fproy3 = s.find(Proyecto.class, 3);

                System.out.printf("Proyecto con id %d:\n %s\n, %s", fproy1.getId(), fproy1, fproy1.getDepartamentos());
                System.out.println();
                System.out.printf("Proyecto con id %d:\n %s\n, %s", fproy2.getId(), fproy2, fproy2.getDepartamentos());
                System.out.println();
                System.out.printf("Proyecto con id %d:\n %s\n, %s", fproy3.getId(), fproy3, fproy3.getDepartamentos());


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
