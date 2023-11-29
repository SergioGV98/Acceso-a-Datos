package org.example;

import org.example.orm.Sede;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class _5_ {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Sede sede = new Sede();
                sede.setNomSede("Ubisoft");
                s.save(sede);
                Sede sede2 = new Sede();
                sede2.setNomSede("Riot Games");
                s.save(sede2);
                t.commit();
                System.out.println("Datos introducidos correctamente.");
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                if (t != null) {
                    System.out.println("ERROR: ROLLBACK");
                    t.rollback();
                }
            }
        }

    }
}
