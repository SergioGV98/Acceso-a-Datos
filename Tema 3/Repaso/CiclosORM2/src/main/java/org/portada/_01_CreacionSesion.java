package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class _01_CreacionSesion {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {

            Session s = sessionFactory.openSession();
            System.out.println("Sesión iniciada");
        }
    }
}
