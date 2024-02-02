package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.*;


public class _06_CrearProyecto {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Proyecto p1 = new Proyecto("Mistborn");
                session.persist(p1);
                Proyecto p2 = new Proyecto("Harry Potter");
                session.persist(p2);

                Departamento departamento = session.get(Departamento.class, 1);
                Departamento departamento2 = session.get(Departamento.class, 2);
                Departamento departamento3 = session.get(Departamento.class, 3);
                p1.getDepartamento().add(departamento);
                p1.getDepartamento().add(departamento2);
                p2.getDepartamento().add(departamento3);

                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}
