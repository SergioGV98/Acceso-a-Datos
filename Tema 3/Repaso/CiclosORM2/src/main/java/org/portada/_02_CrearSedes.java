package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Sede;

//Todo. Pon hibernate.hbm2ddl.auto en create
public class _02_CrearSedes {
    public static void main(String[] args) {

        Sede sede1 = new Sede("Sede1");
        Sede sede2 = new Sede("Sede2");
        //Se creo una tercera sede para que esté relacionado con ninguna tabla
        //por tanto se puede eliminar.
        Sede sede3 = new Sede("Sede3");

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.persist(sede1);
                s.persist(sede2);
                s.persist(sede3);

                t.commit();

                Sede findSede1 = s.find(Sede.class,sede1.getId());
                Sede findSede2 = s.find(Sede.class,sede2.getId());
                System.out.printf("Nueva sede creado con id %d: %s\n", findSede1.getId(), findSede1);
                System.out.printf("Nueva sede creado con id %d: %s\n", findSede2.getId(), findSede2);

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
