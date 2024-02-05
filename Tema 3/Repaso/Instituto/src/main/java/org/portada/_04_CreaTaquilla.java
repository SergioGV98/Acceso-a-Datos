package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Alumno;
import org.portada.modelo.Grupo;
import org.portada.modelo.Taquilla;

import java.time.LocalDate;

public class _04_CreaTaquilla {
    public static void main(String[] args) {
        Taquilla t1 = new Taquilla(1);
        Taquilla t2 = new Taquilla(2);
        Taquilla t3 = new Taquilla(3);

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Alumno a1 = s.find(Alumno.class, 1);
                Alumno a2 = s.find(Alumno.class, 2);
                Alumno a3 = s.find(Alumno.class, 3);

                t1.setAlumno(a1);
                t2.setAlumno(a2);
                t3.setAlumno(a3);

                s.persist(t1);
                s.persist(t2);
                s.persist(t3);

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
