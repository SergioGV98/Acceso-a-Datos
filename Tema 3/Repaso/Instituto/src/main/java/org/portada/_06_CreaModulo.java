package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Alumno;
import org.portada.modelo.Modulo;
import org.portada.modelo.Taquilla;

import java.util.Arrays;

public class _06_CreaModulo {
    public static void main(String[] args) {

        Modulo m1 = new Modulo("Acceso a datos");
        Modulo m2 = new Modulo("Interfaces");
        Modulo m3 = new Modulo("Formacion y orientacion laboral");


        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.persist(m1);
                s.persist(m2);
                s.persist(m3);

                Alumno a1 = s.find(Alumno.class, 1);
                Alumno a2 = s.find(Alumno.class, 2);
                Alumno a3 = s.find(Alumno.class, 3);

                a1.getModulos().addAll(Arrays.asList(m1, m2, m3));
                a2.getModulos().addAll(Arrays.asList(m1, m2, m3));
                a3.getModulos().addAll(Arrays.asList(m1, m2, m3));

                t.commit();

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