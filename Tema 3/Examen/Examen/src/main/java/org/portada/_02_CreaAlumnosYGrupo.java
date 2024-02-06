package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.portada.modelo.Alumno;

import org.hibernate.cfg.Configuration;
import org.portada.modelo.Grupo;

import java.time.LocalDate;

public class _02_CreaAlumnosYGrupo {
    public static void main(String[] args) {
        Alumno a1 = new Alumno("Sergio", LocalDate.of(1998, 7, 2));
        Alumno a2 = new Alumno("Adrian", LocalDate.of(2001, 6, 2));
        Alumno a3 = new Alumno("Carlos", LocalDate.of(2004, 1, 22));

        Grupo g1 = new Grupo("DAM");
        Grupo g2 = new Grupo("DAW");

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.persist(g1);
                s.persist(g2);

                a1.setGrupo(g1);
                a2.setGrupo(g2);
                a3.setGrupo(g1);
                s.persist(a1);
                s.persist(a2);
                s.persist(a3);

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
