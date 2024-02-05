package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Taquilla;

import java.util.List;

public class _05_BuscaTaquillas {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {
            try {

                String hql = "FROM Taquilla";
                List<Taquilla> taquillas = s.createQuery(hql, Taquilla.class).getResultList();

                for (Taquilla t : taquillas) {
                    System.out.println(t.toString());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
