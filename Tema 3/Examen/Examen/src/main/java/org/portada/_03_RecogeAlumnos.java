package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Alumno;
import org.portada.modelo.Grupo;

public class _03_RecogeAlumnos {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {
            try {

                Alumno a1 = s.find(Alumno.class, 1);
                Grupo g1 = s.find(Grupo.class, 1);

                System.out.println(a1.toString());
                System.out.println(g1.getAlumnos());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}