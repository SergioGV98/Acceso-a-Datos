package org.example;

import org.example.orm.InfoAlmacenProd;
import org.example.orm.Productos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.Collection;

public class _2_ {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");
            Transaction t = null;
            try {
                t = s.beginTransaction();
                InfoAlmacenProd info = s.get(InfoAlmacenProd.class, 1);
                System.out.println(info.toString());
                t.commit();
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
