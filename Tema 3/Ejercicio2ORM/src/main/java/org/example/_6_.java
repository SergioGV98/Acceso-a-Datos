package org.example;

import org.example.orm.Departamento;
import org.example.orm.Empleado;
import org.example.orm.Sede;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class _6_ {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Sede sede = s.get(Sede.class, 3);

                Departamento marketing = new Departamento();
                marketing.setNomDepto("DP Marketing Riot Games");
                marketing.setSedeByIdSede(sede);
                s.save(marketing);

                Departamento programacion = new Departamento();
                programacion.setNomDepto("DP Programacion Montrereal");
                sede = s.get(Sede.class, 2);
                programacion.setSedeByIdSede(sede);
                s.save(programacion);

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
