package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Departamento;
import org.portada.modelo.Empleado;
import org.portada.modelo.Sede;

public class _04_CreaSedeDepartamentoEmpleado {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();

                // Sede 1
                Sede s1 = new Sede("Bosonit");
                s.persist(s1);

                // Departamento 1
                Departamento d1 = new Departamento("Android studio");
                s.persist(d1);

                Departamento d2 = new Departamento("Flutter");
                s.persist(d2);

                // Empleado 1
                Empleado e1 = new Empleado("Sergio", 1);
                s.persist(e1);
                Empleado e2 = new Empleado("Carlos", 2);
                s.persist(e2);
                s1.getDepartamentos().add(d1);
                d1.getEmpleado().add(e1);
                s1.getDepartamentos().add(d2);
                d1.getEmpleado().add(e2);
                t.commit();

                s.refresh(s1);
                System.out.println(s1);

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
