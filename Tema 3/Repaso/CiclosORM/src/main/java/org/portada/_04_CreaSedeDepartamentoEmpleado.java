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
                Departamento d1 = new Departamento("Android studio", s1);
                s.persist(d1);

                // Empleado 1
                Empleado e1 = new Empleado("Sergio", 1, d1);
                s.persist(e1);


                // Sede 2
                Sede s2 = new Sede("Accenture");
                s.persist(s2);

                // Departamento 2
                Departamento d2 = new Departamento("Flutter", s2);
                s.persist(d2);

                // Empleado 2
                Empleado e2 = new Empleado("Carlos", 2, d2);
                s.persist(e2);

                // Departamento 3
                Departamento d3 = new Departamento("Unity", s2);
                s.persist(d3);

                // Empleado 3
                Empleado e3 = new Empleado("Miguel", 3, d3);
                s.persist(e3);
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
