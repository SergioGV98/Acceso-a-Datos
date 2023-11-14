package org.example;

import org.example.orm.Cliente;
import org.example.orm.Producto;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                Cliente cli = new Cliente();
                cli.setDni("34235425J");
                cli.setNomCliente("IBAÑEZ");
                s.save(cli);
                Producto prod = new Producto();
                prod.setCodProd(1);
                prod.setNomProd("BOLI AZUL");
                prod.setPrUnit(new BigDecimal(0.90));
                s.save(prod);
                t.commit();
                System.out.printf("Identificador %d para nuevo cliente: %s-%s.\n",
                        cli.getIdCliente(), cli.getDni(), cli.getNomCliente());
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