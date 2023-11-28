package org.example;

import org.example.orm.Cliente;
import org.example.orm.Factura;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Ejercicio1 {
    public static void main(String[] args) {
        //Conexion a la base de datos
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");
            Transaction t = null;
            try {
                t = s.beginTransaction();
                Cliente cli = new Cliente();
                cli.setDni("34235425J");
                cli.setNomCliente("IBÁÑEZ");
                s.save(cli);

                Factura f = new Factura();
                f.setCliente(cli);
                f.setFechaFactura(new java.util.Date());
                s.save(f);
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