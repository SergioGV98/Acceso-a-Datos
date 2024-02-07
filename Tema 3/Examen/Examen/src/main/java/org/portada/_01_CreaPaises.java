package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Pais;
import org.portada.modelo.Presidente;

import java.time.LocalDate;

public class _01_CreaPaises {
    public static void main(String[] args) {
        Pais espana = new Pais("España");
        Pais francia = new Pais("Francia");
        Pais inglaterra = new Pais("Inglaterra");

        Presidente sergio = new Presidente("Sergio");
        Presidente miguel = new Presidente("Miguel");


        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.persist(sergio);
                s.persist(miguel);

                espana.setPresidente(sergio);
                s.persist(espana);
                francia.setPresidente(miguel);
                s.persist(francia);
                s.persist(inglaterra);

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
