package org.example;

import org.example.orm.InfoAlmacenProd;
import org.example.orm.Productos;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import java.math.BigDecimal;

public class _1_ {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");
            Transaction t = null;
            try {
                t = s.beginTransaction();
                Productos producto = new Productos();
                producto.setCodProd(1);
                producto.setNomProd("Nacidos de la bruma: El imperio final");
                producto.setPrUnit(BigDecimal.valueOf(29.95));
                producto.setDescr("Primera entrega de nacidos de la bruma.");
                s.save(producto);

                InfoAlmacenProd almacen = new InfoAlmacenProd();
                almacen.setCodProd(1);
                almacen.setExistencias(BigDecimal.valueOf(15));
                s.save(almacen);
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