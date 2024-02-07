package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Pais;
import org.portada.modelo.Presidente;
import org.portada.modelo.Region;

public class _02_CrearPaisesConRegiones {
    public static void main(String[] args) {
        Pais estadosUnidos = new Pais("Estados Unidos");
        Region colorado = new Region("Colorado");
        Region texas = new Region("Texas");
        Region dalas = new Region("Dalas");



        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.persist(estadosUnidos);
                colorado.setPais(estadosUnidos);
                s.persist(colorado);
                texas.setPais(estadosUnidos);
                s.persist(texas);
                dalas.setPais(estadosUnidos);
                s.persist(dalas);
                t.commit();

                s.refresh(estadosUnidos);
                System.out.printf("Pais %s con regiones %s\n", estadosUnidos.getNombre(), estadosUnidos.getRegiones().toString());
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
