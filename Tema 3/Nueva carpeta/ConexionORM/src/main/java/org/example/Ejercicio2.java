package org.example;

import org.example.orm.Sede;
import org.example.orm.Proyecto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

public class Ejercicio2 {
    public static void main(String[] args) {
        //Introducir datos en sede y proyecto
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();
                //Sede sede = new Sede();
                //sede.setNomSede("Alcantarilla");
                //s.save(sede);
                Proyecto proyecto = new Proyecto();
                proyecto.setfInicio(LocalDateTime.of(1998, 12, 1).toLocalDate());
                proyecto.setfFin(new Date(2014, Calendar.FEBRUARY, 14));
                proyecto.setNomProy("Proyecto Android");
                s.save(proyecto);
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

/*
RECUPERAR CLIENTE
int idCliente = 3;
Cliente cl = s.get(Cliente.class, idCliente);

 */

/*
UPDATE CLIENTE
int cliente = 3;
Client cl = s.get(Cliente.class, idClientE);
cl.setNomCliente("IBAÑEZ");
s.
 */