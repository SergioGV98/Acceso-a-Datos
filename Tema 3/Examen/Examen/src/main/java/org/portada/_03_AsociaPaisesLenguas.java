package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Lengua;
import org.portada.modelo.Pais;
import org.portada.modelo.Presidente;

import java.util.Arrays;

public class _03_AsociaPaisesLenguas {
    public static void main(String[] args) {
        Pais italia = new Pais("Italia");
        Pais suiza = new Pais("Suiza");

        Lengua italiano = new Lengua("IT", "Italiano");
        Lengua aleman = new Lengua("AL", "Aleman");
        Lengua frances = new Lengua("FR", "Frances");

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.persist(italia);
                s.persist(suiza);

                s.persist(italiano);
                s.persist(aleman);
                s.persist(frances);

                italia.getLenguas().addAll(Arrays.asList(italiano, aleman));
                suiza.getLenguas().addAll(Arrays.asList(italiano, aleman, frances));
                t.commit();

                System.out.printf("Pais %s con lenguas: %s\n", italia.getNombre(), italia.getLenguas().toString());
                System.out.println();
                System.out.printf("Pais %s con lenguas: %s\n", suiza.getNombre(), suiza.getLenguas().toString());
                System.out.println();

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
