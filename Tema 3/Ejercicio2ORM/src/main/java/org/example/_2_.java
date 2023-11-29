package org.example;

import org.example.orm.Proyecto;
import org.example.orm.Sede;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Calendar;

public class _2_ {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Sede sede = new Sede();
                sede.setNomSede("Alcantarilla");
                s.save(sede);

                Proyecto proyecto = new Proyecto();
                proyecto.setfInicio(new Date(1998, Calendar.DECEMBER, 1));
                proyecto.setfFin(new Date(2014, Calendar.FEBRUARY, 14));
                proyecto.setNomProy("Proyecto Android");
                s.save(proyecto);

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
