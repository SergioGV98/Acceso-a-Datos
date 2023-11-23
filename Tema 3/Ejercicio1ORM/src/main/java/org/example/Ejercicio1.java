package org.example;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class Ejercicio1 {
    public static void main(String[] args) {
        //Conexion a la base de datos
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");
        }
    }
}