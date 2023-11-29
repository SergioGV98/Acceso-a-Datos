package org.example;

import org.example.orm.Departamento;
import org.example.orm.Empleado;
import org.example.orm.Sede;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class _8_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                System.out.println("Escribeme el codigo de la sede");
                int codSede = Integer.parseInt(sc.nextLine());
                Sede sede = s.get(Sede.class, codSede);
                System.out.println(sede.toString());
                //Departamento departamento = s.get(Departamento.class, sede.getIdSede());
                t.commit();
                //System.out.println(departamento.toString());
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
